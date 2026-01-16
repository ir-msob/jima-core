package ir.msob.jima.core.commons.annotation;

import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.util.ClassAnnotationInfo;
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
    void hasAnnotation_ShouldReturnTrue_WhenAnnotationIsPresent() {
        // Arrange
        @DomainInfo(domainName = "testDomain", parentDomainName = "parentDomain")
        class AnnotatedClass {
        }

        // Act
        boolean hasAnnotation = annotationInfo.hasAnnotation(AnnotatedClass.class);

        // Assert
        assertTrue(hasAnnotation, "AnnotatedClass should have DomainInfo annotation");
    }

    @Test
    void hasAnnotation_ShouldReturnFalse_WhenAnnotationIsAbsent() {
        // Arrange
        class NonAnnotatedClass {
        }

        // Act
        boolean hasAnnotation = annotationInfo.hasAnnotation(NonAnnotatedClass.class);

        // Assert
        assertFalse(hasAnnotation, "NonAnnotatedClass should not have DomainInfo annotation");
    }

    @Test
    void getAnnotation_ShouldReturnAnnotation_WhenAnnotationIsPresent() {
        // Arrange
        @DomainInfo(domainName = "testDomain", parentDomainName = "parentDomain")
        class AnnotatedClass {
        }

        // Act
        DomainInfo annotation = annotationInfo.getAnnotation(AnnotatedClass.class);

        // Assert
        assertNotNull(annotation, "AnnotatedClass should have DomainInfo annotation");
        assertEquals("testDomain", annotation.domainName(), "Domain name should match");
        assertEquals("parentDomain", annotation.parentDomainName(), "Parent domain name should match");
    }

    @Test
    void getAnnotation_ShouldReturnNull_WhenAnnotationIsAbsent() {
        // Arrange
        class NonAnnotatedClass {
        }

        // Act
        DomainInfo annotation = annotationInfo.getAnnotation(NonAnnotatedClass.class);

        // Assert
        assertNull(annotation, "NonAnnotatedClass should not have DomainInfo annotation");
    }

    @Test
    void getAnnotation_ShouldThrowException_WhenClassIsNull() {
        // Act & Assert
        assertThrows(CommonRuntimeException.class, () -> annotationInfo.getAnnotation(null), "Should throw CommonRuntimeException for null class");
    }
}
