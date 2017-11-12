package de.hilling.training.annotations.processing;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

// tag::declaration[]
public class EmailAddressValidator extends AbstractProcessor {
    // end::declaration[]

    static final String DEFAULT_ERROR_MESSAGE = EMailAddress.class
                                                .getSimpleName() + " annotated field must be" + " a String";

    private Messager messager;
    private Types    types;
    private Elements elements;

    // tag::registration[]
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>();
        result.add(EMailAddress.class.getCanonicalName());
        return result;
    }
    // end::registration[]

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        types = processingEnv.getTypeUtils();
        elements = processingEnv.getElementUtils();
    }

    // tag::implementation[]
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Set<? extends Element> annotatedFields = roundEnv.getElementsAnnotatedWith(EMailAddress.class);
        for (Element mailField : annotatedFields) {
            verifyElement(mailField);
        }
        return false;
    }
    // end::implementation[]

    private void verifyElement(Element mailField) {
        final TypeMirror annotatedType = mailField.asType();
        final TypeMirror expectedType = elements.getTypeElement(String.class.getCanonicalName()).asType();
        if (!types.isSameType(annotatedType, expectedType)) {
            messager.printMessage(Diagnostic.Kind.ERROR, DEFAULT_ERROR_MESSAGE, mailField);
        }
    }
}
