package de.hilling.training.annotations.processing;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

import javax.tools.JavaFileObject;

import org.junit.Test;

import com.google.testing.compile.JavaFileObjects;

public class EMailAddressProcessorTest {

    @Test
    public void validateValidObject() {
        assert_().about(javaSource())
                 .that(source(CorrectlyAnnotatedClass.class))
                 .processedWith(new EmailAddressValidator())
                 .compilesWithoutError();
    }

    @Test
    public void failOnSemanticallyInvalidAnnotation() {
        assert_().about(javaSource())
                 .that(source(IncorrectlyAnnotatedClass.class))
                 .processedWith(new EmailAddressValidator())
                 .failsToCompile()
                 .withErrorContaining(EmailAddressValidator.DEFAULT_ERROR_MESSAGE);
    }

    private JavaFileObject source(Class<?> clazz) {
        return JavaFileObjects.forResource(clazz.getCanonicalName().replace('.', '/') + ".java");
    }


}
