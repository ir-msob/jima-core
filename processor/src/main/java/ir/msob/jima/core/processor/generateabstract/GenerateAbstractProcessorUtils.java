package ir.msob.jima.core.processor.generateabstract;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for the annotation processor.
 */
public class GenerateAbstractProcessorUtils {
    private GenerateAbstractProcessorUtils() {
    }

    /**
     * Extracts generic type parameters from the abstract superclass specified in the annotation.
     *
     * @param processingEnv The processing environment.
     * @param annotation    The annotation.
     * @return A map of generic type parameter names to their corresponding TypeParameterElement.
     */
    public static Map<String, TypeParameterElement> extractGenericTypes(ProcessingEnvironment processingEnv, GenerateAbstract annotation) {
        TypeMirror abstractClassTypeMirror = getAbstractClassTypeMirror(annotation);
        if (abstractClassTypeMirror == null) {
            return new HashMap<>(); // Return empty map if no superclass
        }
        TypeElement abstractSuperClassElement = (TypeElement) processingEnv.getTypeUtils().asElement(abstractClassTypeMirror);

        List<? extends TypeParameterElement> abstractClassTypeParameters = abstractSuperClassElement.getTypeParameters();
        Map<String, TypeParameterElement> abstractClassTypeParamsMap = new HashMap<>();

        for (TypeParameterElement tp : abstractClassTypeParameters) {
            abstractClassTypeParamsMap.put(tp.toString(), tp);
        }

        return abstractClassTypeParamsMap;
    }

    /**
     * Gets the TypeMirror of the abstract class from the annotation.
     *
     * @param annotation The annotation.
     * @return The TypeMirror of the abstract class.
     */
    public static TypeMirror getAbstractClassTypeMirror(GenerateAbstract annotation) {
        try {
            annotation.value();
        } catch (MirroredTypeException mte) {
            return mte.getTypeMirror();
        }
        return null;
    }

    /**
     * Gets the name of the abstract superclass from the TypeMirror.
     *
     * @param abstractClassTypeMirror The TypeMirror of the abstract class.
     * @return The name of the abstract superclass.
     */
    public static String getAbstractSuperClassName(TypeMirror abstractClassTypeMirror) {
        if (abstractClassTypeMirror == null) {
            return ""; // Return empty string if no superclass is specified
        } else {
            return abstractClassTypeMirror.toString();
        }
    }

    /**
     * Generates the generic types declaration for a class.
     *
     * @param typeParameters The list of type parameters.
     * @param imports        The set of imports.
     * @return The generic types declaration string.
     */
    public static String generateGenericTypesDeclaration(List<? extends TypeParameterElement> typeParameters, Set<String> imports) {
        if (typeParameters.isEmpty()) {
            return "";
        }

        return "<" + typeParameters.stream().map(tp -> {
            String typeParam = tp.toString();
            if (!tp.getBounds().isEmpty()) {
                List<? extends TypeMirror> bounds = tp.getBounds();
                if (bounds.size() == 1 && bounds.getFirst().toString().equals("java.lang.Object")) {
                    // Don't add "extends Object"
                } else {
                    typeParam += " extends " + bounds.stream().map(bound -> getSimpleName(bound.toString())).collect(Collectors.joining(" & "));
                    for (TypeMirror bound : bounds) {
                        addTypeToImports(imports, bound);
                    }
                }
            }
            return typeParam;
        }).collect(Collectors.joining(", ")) + ">";
    }

    /**
     * Generates the generic types extends clause for a class.
     *
     * @param typeParameters The map of type parameter names to their corresponding TypeParameterElement.
     * @param imports        The set of imports.
     * @return The generic types extends clause string.
     */
    public static String generateGenericTypesExtends(Map<String, TypeParameterElement> typeParameters, Set<String> imports) {
        if (typeParameters.isEmpty()) {
            return "";
        }

        typeParameters.values().stream().flatMap(tp -> tp.getBounds().stream()).forEach(bound -> addTypeToImports(imports, bound));

        return "<" + String.join(", ", typeParameters.keySet()) + ">";
    }

    /**
     * Generates the generic types implements clause for a class.
     *
     * @param typeParameters The list of type parameters.
     * @return The generic types implements clause string.
     */
    public static String generateGenericTypesImplements(List<? extends TypeParameterElement> typeParameters) {
        if (typeParameters.isEmpty()) {
            return "";
        }

        return "<" + typeParameters.stream().map(TypeParameterElement::toString).collect(Collectors.joining(", ")) + ">";
    }

    /**
     * Gets the parameterized abstract superclass name.
     *
     * @param abstractSuperClass  The name of the abstract superclass.
     * @param genericTypesExtends The generic types extends clause.
     * @return The parameterized abstract superclass name.
     */
    public static String getParameterizedAbstractSuperClass(String abstractSuperClass, String genericTypesExtends) {
        if (abstractSuperClass.contains("<")) {
            String baseSuperClass = abstractSuperClass.substring(0, abstractSuperClass.indexOf("<"));
            return baseSuperClass + genericTypesExtends;
        } else if (!genericTypesExtends.isEmpty()) {
            return abstractSuperClass + genericTypesExtends;
        } else {
            return abstractSuperClass;
        }
    }

    /**
     * Adds the type to the set of imports if it is not a java.lang type and contains a dot.
     *
     * @param imports    The set of imports.
     * @param typeMirror The type mirror.
     */
    public static void addTypeToImports(Set<String> imports, TypeMirror typeMirror) {
        String typeName = typeMirror.toString();
        if (!typeName.startsWith("java.lang") && typeName.contains(".")) {
            imports.add(typeName);
        }
    }

    /**
     * Generates the import statements for the given set of imports.
     *
     * @param imports The set of imports.
     * @return The import statements string.
     */
    public static String generateImports(Set<String> imports) {
        return imports.stream().map(imp -> "import " + imp + ";\n").collect(Collectors.joining()) + "\n";
    }

    /**
     * Decapitalizes the first letter of the string.
     *
     * @param str The string to decapitalize.
     * @return The decapitalized string.
     */
    public static String decapitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * Gets the simple name of a qualified name.
     *
     * @param qualifiedName The qualified name.
     * @return The simple name.
     */
    public static String getSimpleName(String qualifiedName) {
        int lastDot = qualifiedName.lastIndexOf('.');
        if (lastDot == -1) {
            return qualifiedName;
        }
        return qualifiedName.substring(lastDot + 1);
    }

    /**
     * Gets the base type of a qualified name.
     *
     * @param qualifiedName The qualified name.
     * @return The base type.
     */
    public static String getBaseType(String qualifiedName) {
        int genericStart = qualifiedName.indexOf('<');
        if (genericStart == -1) {
            return qualifiedName;
        }
        return qualifiedName.substring(0, genericStart);
    }
}