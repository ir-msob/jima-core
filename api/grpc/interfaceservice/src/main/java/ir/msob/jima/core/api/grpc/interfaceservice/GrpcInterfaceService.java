package ir.msob.jima.core.api.grpc.interfaceservice;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

/**
 * Simple Maven Plugin to add a nested interface CrudServiceI to CrudServiceGrpc.
 */
@Mojo(name = "interface-service", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GrpcInterfaceService extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}", property = "projectBuildDirectory", required = true)
    private String projectBuildDirectory;

    @Parameter(property = "packageName", required = true)
    private String packageName;

    @Parameter(property = "serviceName", required = true)
    private String serviceName;

    @Override
    public void execute() throws MojoExecutionException {
        Log log = getLog();
        log.info("Starting GrpcInterfaceService plugin execution...");

        String outputDirectory = projectBuildDirectory + "/generated-sources/protobuf/" + packageName.replace(".", "/");
        File outputDirectoryFile = new File(outputDirectory);
        log.debug("Resolved output directory: " + outputDirectoryFile.getAbsolutePath());

        String grpcClassFileName = serviceName + "Grpc.java";
        File inputFile = new File(outputDirectoryFile, grpcClassFileName);
        log.debug("Looking for gRPC Java file: " + inputFile.getAbsolutePath());

        if (!inputFile.exists()) {
            throw new MojoExecutionException("gRPC Java file not found: " + inputFile.getAbsolutePath());
        }

        try {
            String inputContent = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);
            log.info("Successfully read gRPC Java file, length: " + inputContent.length());

            CompilationUnit compilationUnit = parseJavaFileContent(inputContent);
            log.debug("Parsed CompilationUnit successfully.");

            // Add CrudServiceI interface
            addCrudServiceInterface(compilationUnit, "CrudServiceI");

            File outputFile = new File(outputDirectoryFile, grpcClassFileName);
            Files.deleteIfExists(outputFile.toPath());
            FileUtils.writeStringToFile(outputFile, compilationUnit.toString(), StandardCharsets.UTF_8);

            log.info("Successfully added CrudServiceI interface to " + grpcClassFileName);

        } catch (IOException e) {
            log.error("IOException occurred while processing the gRPC Java file", e);
            throw new MojoExecutionException("Error processing file: " + grpcClassFileName, e);
        } catch (RuntimeException e) {
            log.error("RuntimeException occurred during parsing or interface addition", e);
            throw new MojoExecutionException("Error modifying file: " + grpcClassFileName, e);
        }
    }

    private CompilationUnit parseJavaFileContent(String content) {
        getLog().debug("Parsing Java file content...");
        ParseResult<CompilationUnit> parseResult = new JavaParser().parse(content);
        return parseResult.getResult()
                .orElseThrow(() -> new RuntimeException("Unable to parse Java file content."));
    }

    private void addCrudServiceInterface(CompilationUnit compilationUnit, String interfaceName) {
        Log log = getLog();
        log.debug("Attempting to add interface: " + interfaceName);

        Optional<ClassOrInterfaceDeclaration> outerClassOpt = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class);
        if (outerClassOpt.isEmpty()) {
            log.warn("No outer class found in compilation unit.");
            return;
        }

        ClassOrInterfaceDeclaration outerClass = outerClassOpt.get();
        log.debug("Found outer class: " + outerClass.getNameAsString());

        boolean exists = outerClass.getMembers().stream()
                .anyMatch(m -> m instanceof ClassOrInterfaceDeclaration ci &&
                        ci.getNameAsString().equals(interfaceName));
        if (exists) {
            log.info(interfaceName + " already exists in the outer class. Skipping addition.");
            return;
        }

        ClassOrInterfaceDeclaration iface = new ClassOrInterfaceDeclaration();
        iface.setName(interfaceName);
        iface.setInterface(true);
        iface.setPublic(true);
        iface.addModifier(Modifier.Keyword.STATIC);
        iface.addExtendedType("io.grpc.BindableService");
        iface.addExtendedType("AsyncService");

        MethodDeclaration bindMethod = new MethodDeclaration();
        bindMethod.addAnnotation(new MarkerAnnotationExpr("Override"));
        bindMethod.setName("bindService");
        bindMethod.setType("io.grpc.ServerServiceDefinition");
        bindMethod.setDefault(true);
        bindMethod.setPublic(true);

        BlockStmt body = new BlockStmt();
        MethodCallExpr call = new MethodCallExpr(new NameExpr(outerClass.getNameAsString()), "bindService",
                com.github.javaparser.ast.NodeList.nodeList(new ThisExpr()));
        body.addStatement(new ReturnStmt(call));
        bindMethod.setBody(body);

        iface.addMember(bindMethod);
        outerClass.addMember(iface);

        log.info("Interface " + interfaceName + " added successfully.");
    }
}
