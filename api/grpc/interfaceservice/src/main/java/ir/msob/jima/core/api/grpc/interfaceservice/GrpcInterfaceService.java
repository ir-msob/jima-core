package ir.msob.jima.core.api.grpc.interfaceservice;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This Mojo (Maven Plugin) generates a gRPC interface service class.
 * <p>
 * This Mojo is annotated as a Maven Plugin and generates a gRPC interface service class during the 'generate-sources' phase.
 * It operates on Java files and modifies the structure of the gRPC interface service based on predefined criteria.
 *
 */
@Mojo(name = "interface-service", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GrpcInterfaceService extends AbstractMojo {

    /**
     * Directory where the generated sources will be stored.
     */
    @Parameter(defaultValue = "${project.build.directory}", property = "projectBuildDirectory", required = true)
    private String projectBuildDirectory;

    /**
     * Package name of the gRPC service.
     */
    @Parameter(property = "packageName", required = true)
    private String packageName;

    /**
     * Name of the gRPC service.
     */
    @Parameter(property = "serviceName", required = true)
    private String serviceName;

    /**
     * Executes the Mojo to generate and modify the gRPC interface service class.
     * <p>
     * This method reads the generated gRPC class file, parses its content, modifies its structure,
     * and writes the updated content back to the file.
     *
     * @throws MojoExecutionException If an error occurs during reading, creating, or editing the Java class.
     */
    public void execute() throws MojoExecutionException {
        String outputDirectory = projectBuildDirectory + "/generated-sources/protobuf/java/" + packageName.replace(".", "/");
        File outputDirectoryFile = new File(outputDirectory);
        String grpcClassName = "Reactor" + serviceName + "Grpc";
        String grpcClassFileName = grpcClassName + ".java";
        String serviceInterfaceName = serviceName + "ImplBase";

        logInfo("Output Directory: " + outputDirectoryFile.getAbsolutePath());

        File inputFile = new File(outputDirectoryFile, grpcClassFileName);
        try {
            String inputContent = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);
            CompilationUnit compilationUnit = parseJavaFileContent(inputContent);

            modifyGrpcServiceClass(compilationUnit, serviceInterfaceName);

            File outputFile = new File(outputDirectoryFile, grpcClassFileName);
            inputFile.delete();
            writeContentToFile(outputFile, compilationUnit.toString());

            logInfo("Generated Java class: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            throw new MojoExecutionException("Error reading/creating/editing the Java class", e);
        }
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to be logged.
     */
    private void logInfo(String message) {
        getLog().info(message);
    }

    /**
     * Parses the content of a Java file and returns the CompilationUnit.
     *
     * @param content The content of the Java file as a string.
     * @return The parsed CompilationUnit representing the Java file content.
     * @throws RuntimeException If the code cannot be parsed.
     */
    private CompilationUnit parseJavaFileContent(String content) {
        ParseResult<CompilationUnit> parseResult = new JavaParser().parse(content);
        return parseResult.getResult().orElseThrow(() -> new RuntimeException("Unable to parse code."));
    }

    /**
     * Modifies the gRPC service class based on the specified service interface name.
     * <p>
     * This method locates the specified service interface in the parsed Java file
     * and updates its structure and method attributes.
     *
     * @param compilationUnit      The CompilationUnit representing the parsed Java file.
     * @param serviceInterfaceName The name of the service interface to be modified.
     */
    private void modifyGrpcServiceClass(CompilationUnit compilationUnit, String serviceInterfaceName) {
        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
            if (classOrInterface.getNameAsString().equals(serviceInterfaceName)) {
                updateClassOrInterface(classOrInterface);
                updateMethods(classOrInterface);
            }
        });
    }

    /**
     * Updates the characteristics of the specified class or interface.
     * <p>
     * This method modifies the attributes of the provided ClassOrInterfaceDeclaration instance to achieve specific changes:
     * - Sets the class as non-abstract.
     * - Sets the class as an interface.
     * - Resets the extended types to match the implemented types.
     * - Removes all implemented types.
     *
     * @param classOrInterface The ClassOrInterfaceDeclaration to be updated.
     */
    private void updateClassOrInterface(ClassOrInterfaceDeclaration classOrInterface) {
        classOrInterface.setAbstract(false);
        classOrInterface.setInterface(true);
        classOrInterface.setExtendedTypes(classOrInterface.getImplementedTypes());
        classOrInterface.setImplementedTypes(new NodeList<>());
    }

    /**
     * Updates the attributes of methods in the given class or interface.
     * <p>
     * This method modifies the method attributes to make them non-final, non-public, non-protected, and default access.
     *
     * @param classOrInterface The class or interface whose methods are to be updated.
     */
    private void updateMethods(ClassOrInterfaceDeclaration classOrInterface) {
        classOrInterface.findAll(MethodDeclaration.class).forEach(method -> {
            method.setFinal(false);
            method.setPublic(false);
            method.setProtected(false);
            method.setDefault(true);
        });
    }

    /**
     * Writes content to a file.
     * <p>
     * This method writes the updated content of the Java file to the specified file.
     *
     * @param file    The file to write the content to.
     * @param content The content to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void writeContentToFile(File file, String content) throws IOException {
        FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
    }
}
