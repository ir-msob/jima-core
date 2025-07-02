package ir.msob.jima.core.processor.generateabstract;

import com.google.auto.service.AutoService;
import com.google.common.base.Strings;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Annotation processor to convert interfaces annotated with {@link GenerateAbstract} to abstract classes.
 */
@SupportedAnnotationTypes("ir.msob.jima.core.processor.generateabstract.GenerateAbstract")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@AutoService(Processor.class)
public class GenerateAbstractProcessor extends AbstractProcessor {

    private static final Logger logger = Logger.getLogger(GenerateAbstractProcessor.class.getName());

    /**
     * Processes the annotations and generates abstract classes for interfaces annotated with {@link GenerateAbstract}.
     *
     * @param annotations The set of annotations found.
     * @param roundEnv    The environment for information about the current and prior round.
     * @return true if the annotations are claimed by this processor, false otherwise.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateAbstract.class)) {
            if (!(element instanceof TypeElement interfaceElement)) {
                continue;
            }

            logger.log(Level.INFO, "Processing interface: {0}", interfaceElement.getQualifiedName());

            GenerateAbstract annotation = element.getAnnotation(GenerateAbstract.class);

            try {
                generateAbstractClass(interfaceElement, annotation);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error generating abstract class: " + e.getMessage());
            }
        }
        return true;
    }

    /**
     * Generates an abstract class for the given interface element.
     *
     * @param interfaceElement The interface element.
     * @param annotation       The annotation instance.
     * @throws IOException If an I/O error occurs.
     */
    private void generateAbstractClass(TypeElement interfaceElement, GenerateAbstract annotation) throws IOException {
        String className = interfaceElement.getSimpleName() + "Abstract";
        String packageName = processingEnv.getElementUtils().getPackageOf(interfaceElement).toString();
        TypeMirror abstractClassTypeMirror = GenerateAbstractProcessorUtils.getAbstractClassTypeMirror(annotation);
        String abstractSuperClass = GenerateAbstractProcessorUtils.getAbstractSuperClassName(abstractClassTypeMirror);

        if (interfaceElement.getKind() == ElementKind.INTERFACE) {
            if (abstractClassTypeMirror != null && !abstractClassTypeMirror.toString().equals("java.lang.Object")) { // A value is set in the annotation
                TypeElement domainElement = (TypeElement) ((DeclaredType) abstractClassTypeMirror).asElement();
                String expectedAbstractClassName = domainElement.getSimpleName().toString();
                TypeElement abstractClassElement = processingEnv.getElementUtils().getTypeElement(packageName + "." + expectedAbstractClassName);
                if (abstractClassElement != null && abstractClassElement.getKind() == ElementKind.CLASS && abstractClassElement.getModifiers().contains(Modifier.ABSTRACT)) {
                    abstractSuperClass = abstractClassElement.getQualifiedName().toString();
                    abstractClassTypeMirror = abstractClassElement.asType();
                } else if (abstractClassElement != null && abstractClassElement.getKind() == ElementKind.INTERFACE) {
                    abstractSuperClass = abstractClassElement.getQualifiedName().toString() + "Abstract";
                    abstractClassTypeMirror = abstractClassElement.asType();
                } else {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "No valid abstract class " + expectedAbstractClassName + " found in the same package for interface " + domainElement.getSimpleName());
                    return;
                }
            }
        }

        Map<String, TypeParameterElement> abstractClassTypeParamsMap = GenerateAbstractProcessorUtils.extractGenericTypes(processingEnv, annotation);
        List<? extends TypeParameterElement> interfaceTypeParameters = interfaceElement.getTypeParameters();

        String classContent = generateClassContent(packageName, className, interfaceElement, abstractSuperClass, abstractClassTypeParamsMap, interfaceTypeParameters);

        processingEnv.getFiler().createSourceFile(packageName + "." + className).openWriter().append(classContent).close();
    }

    /**
     * Generates the content of the abstract class.
     *
     * @param packageName                The package name.
     * @param className                  The class name.
     * @param interfaceElement           The interface element.
     * @param abstractSuperClass         The abstract superclass name.
     * @param abstractClassTypeParamsMap The map of abstract class type parameters.
     * @param interfaceTypeParameters    The list of interface type parameters.
     * @return The generated class content as a string.
     */
    private String generateClassContent(String packageName, String className, TypeElement interfaceElement, String abstractSuperClass, Map<String, TypeParameterElement> abstractClassTypeParamsMap, List<? extends TypeParameterElement> interfaceTypeParameters) {
        StringBuilder classContent = new StringBuilder();
        Set<String> imports = new HashSet<>();

        String genericTypesDeclaration = GenerateAbstractProcessorUtils.generateGenericTypesDeclaration(interfaceTypeParameters, imports);
        String genericTypesExtends = GenerateAbstractProcessorUtils.generateGenericTypesExtends(abstractClassTypeParamsMap, imports);
        String genericTypesImplements = GenerateAbstractProcessorUtils.generateGenericTypesImplements(interfaceTypeParameters);

        classContent.append("package ").append(packageName).append(";\n\n");

        String parameterizedAbstractSuperClass = GenerateAbstractProcessorUtils.getParameterizedAbstractSuperClass(abstractSuperClass, genericTypesExtends);
        String interfaceName = interfaceElement.getQualifiedName().toString();

        // Import the interface
        imports.add(interfaceName);

        String extendsClause = "";
        if (!abstractSuperClass.equals("java.lang.Object") && !Strings.isNullOrEmpty(abstractSuperClass)) { // Check if value was set and is not Object
            extendsClause = " extends " + GenerateAbstractProcessorUtils.getSimpleName(GenerateAbstractProcessorUtils.getBaseType(parameterizedAbstractSuperClass)) + genericTypesExtends;
            // Import the superclass (if it's NOT java.lang.Object)
            imports.add(GenerateAbstractProcessorUtils.getBaseType(parameterizedAbstractSuperClass));
        }

        String classDeclaration = "public abstract class " + className + genericTypesDeclaration + extendsClause + " implements " + GenerateAbstractProcessorUtils.getSimpleName(interfaceName) + genericTypesImplements + " {\n\n";

        classContent.append(GenerateAbstractProcessorUtils.generateImports(imports)).append(classDeclaration);

        classContent.append(generateFields(interfaceElement, imports));
        classContent.append(generateGetterSetterMethods(interfaceElement, imports));

        classContent.append("}\n");
        return classContent.toString();
    }

    /**
     * Generates the fields for the abstract class based on the interface methods.
     *
     * @param interfaceElement The interface element.
     * @param imports          The set of imports.
     * @return The generated fields as a string.
     */
    private String generateFields(TypeElement interfaceElement, Set<String> imports) {
        StringBuilder fieldsContent = new StringBuilder();
        for (Element member : interfaceElement.getEnclosedElements()) {
            if (member.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) member;
                if (method.getSimpleName().toString().startsWith("get") && method.getParameters().isEmpty()) {
                    TypeMirror returnType = method.getReturnType();
                    String fieldType = returnType.toString();
                    String fieldName = GenerateAbstractProcessorUtils.decapitalize(method.getSimpleName().toString().substring(3));
                    fieldsContent.append(" private ").append(GenerateAbstractProcessorUtils.getSimpleName(fieldType)).append(" ").append(fieldName).append(";\n"); // Use simple name
                    GenerateAbstractProcessorUtils.addTypeToImports(imports, returnType);
                }
                for (VariableElement param : method.getParameters()) {
                    TypeMirror paramType = param.asType();
                    GenerateAbstractProcessorUtils.addTypeToImports(imports, paramType);
                }
            }
        }
        return fieldsContent.toString();
    }

    /**
     * Generates the getter and setter methods for the abstract class based on the interface methods.
     *
     * @param interfaceElement The interface element.
     * @param imports          The set of imports.
     * @return The generated getter and setter methods as a string.
     */
    private String generateGetterSetterMethods(TypeElement interfaceElement, Set<String> imports) {
        StringBuilder methodsContent = new StringBuilder();
        for (Element member : interfaceElement.getEnclosedElements()) {
            if (member.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) member;
                String methodName = method.getSimpleName().toString();
                TypeMirror returnTypeMirror = method.getReturnType();
                String returnType = returnTypeMirror.toString();

                if (methodName.startsWith("get") && method.getParameters().isEmpty()) {
                    String fieldName = GenerateAbstractProcessorUtils.decapitalize(methodName.substring(3));
                    methodsContent.append("\n @Override\n public ").append(GenerateAbstractProcessorUtils.getSimpleName(returnType)).append(" ").append(methodName).append("() {\n") // Use simple name
                            .append(" return this.").append(fieldName).append(";\n }\n");
                } else if (methodName.startsWith("set") && method.getParameters().size() == 1) {
                    String fieldName = GenerateAbstractProcessorUtils.decapitalize(methodName.substring(3));
                    VariableElement param = method.getParameters().get(0);
                    TypeMirror paramTypeMirror = param.asType();
                    String paramType = paramTypeMirror.toString();

                    methodsContent.append("\n @Override\n public void ").append(methodName)
                            .append("(").append(GenerateAbstractProcessorUtils.getSimpleName(paramType)).append(" ").append(fieldName).append(") {\n") // Use simple name
                            .append(" this.").append(fieldName).append(" = ").append(fieldName).append(";\n }\n");
                }
            }
        }
        return methodsContent.toString();
    }
}