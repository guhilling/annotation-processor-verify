package de.hilling.training.annotations.processing;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

// tag::declaration[]
@SupportedAnnotationTypes("de.hilling.training.annotations.processing.EMailAddress")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EmailAddressValidator extends AbstractProcessor {
    // end::declaration[]

    static final String DEFAULT_ERROR_MESSAGE = EMailAddress.class
                                                       .getSimpleName() + " annotated field must be" + " a String";

    private Messager messager;
    private Types    types;
    private Elements elements;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        types = processingEnv.getTypeUtils();
        elements = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Set<? extends Element> annotatedFields = roundEnv.getElementsAnnotatedWith(EMailAddress.class);
        for (Element mailField : annotatedFields) {
            final TypeMirror annotatedType = mailField.asType();
            final TypeMirror expectedType = elements.getTypeElement(String.class.getCanonicalName()).asType();
            if (!types.isSameType(annotatedType, expectedType)) {
                messager.printMessage(Diagnostic.Kind.ERROR, DEFAULT_ERROR_MESSAGE, mailField);
            }
        }
        return false;
    }
}
