package de.hilling.training.annotations.processing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Diese Annotation markiert das annotatierte Feld als Email-Adresse.
 * <p>
 * Der zugehörige Annotation Processor prüft, ob das Feld vom Typ String ist.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EMailAddress {
}
