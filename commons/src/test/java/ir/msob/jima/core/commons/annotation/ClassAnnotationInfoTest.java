package ir.msob.jima.core.commons.annotation;

import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.shared.annotation.ClassAnnotationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassAnnotationInfoTest {

    private ClassAnnotationInfo<DomainInfo> annotationInfo;

    @BeforeEach
    void setUp() {
        annotationInfo = new ClassAnnotationInfo<>(DomainInfo.class);
    }

    @Test
    void testHasAnnotation_WhenAnnotationIsPresent_ShouldReturnTrue() {
        // Arrange
        @DomainInfo(serviceName = "value", version = "value", domainName = "value")
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
        @DomainInfo(serviceName = "value", version = "value", domainName = "value")
        class AnnotatedClass {
        }

        // Act
        DomainInfo annotation = annotationInfo.getAnnotation(AnnotatedClass.class);

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
        DomainInfo annotation = annotationInfo.getAnnotation(NonAnnotatedClass.class);

        // Assert
        assertNull(annotation, "NonAnnotatedClass should not have MyAnnotation");
    }

    @Test
    void testGetAnnotation_WhenClassIsNull_ShouldThrowException() {
        // Act and Assert
        assertThrows(CommonRuntimeException.class, () -> annotationInfo.getAnnotation(null), "Should throw CommonRuntimeException for null class");
    }

}
