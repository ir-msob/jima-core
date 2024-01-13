package ir.msob.jima.core.commons.annotation;

import ir.msob.jima.core.commons.annotation.domain.DomainService;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassAnnotationInfoTest {

    private ClassAnnotationInfo<DomainService> annotationInfo;

    @BeforeEach
    void setUp() {
        annotationInfo = new ClassAnnotationInfo<>(DomainService.class);
    }

    @Test
    void testHasAnnotation_WhenAnnotationIsPresent_ShouldReturnTrue() {
        // Arrange
        @DomainService(serviceName = "value", version = "value", domainName = "value")
        class AnnotatedClass {
        }

        // Act
        boolean hasAnnotation = annotationInfo.hasAnnotation(AnnotatedClass.class);

        // Assert
        assertTrue(hasAnnotation, "AnnotatedClass should have MyAnnotation");
    }

    @Test
    void testHasAnnotation_WhenAnnotationIsAbsent_ShouldReturnFalse() {
        // Arrange
        class NonAnnotatedClass {
        }

        // Act
        boolean hasAnnotation = annotationInfo.hasAnnotation(NonAnnotatedClass.class);

        // Assert
        assertFalse(hasAnnotation, "NonAnnotatedClass should not have MyAnnotation");
    }

    @Test
    void testGetAnnotation_WhenAnnotationIsPresent_ShouldReturnAnnotation() {
        // Arrange
        @DomainService(serviceName = "value", version = "value", domainName = "value")
        class AnnotatedClass {
        }

        // Act
        DomainService annotation = annotationInfo.getAnnotation(AnnotatedClass.class);

        // Assert
        assertNotNull(annotation, "AnnotatedClass should have MyAnnotation");
        assertEquals("value", annotation.serviceName(), "Annotation value should match");
    }

    @Test
    void testGetAnnotation_WhenAnnotationIsAbsent_ShouldReturnNull() {
        // Arrange
        class NonAnnotatedClass {
        }

        // Act
        DomainService annotation = annotationInfo.getAnnotation(NonAnnotatedClass.class);

        // Assert
        assertNull(annotation, "NonAnnotatedClass should not have MyAnnotation");
    }

    @Test
    void testGetAnnotation_WhenClassIsNull_ShouldThrowException() {
        // Act and Assert
        assertThrows(CommonRuntimeException.class, () -> annotationInfo.getAnnotation(null), "Should throw CommonRuntimeException for null class");
    }

}
