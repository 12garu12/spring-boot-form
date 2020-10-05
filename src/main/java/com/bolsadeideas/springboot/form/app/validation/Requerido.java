package com.bolsadeideas.springboot.form.app.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/* Anotacion personalizada */
@Constraint(validatedBy = RequeridoValidador.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface Requerido {

    /* Estos tres metodos fueron copiados de la anotacion @NotBlank*/
    String message() default "El campo es requerido - usando Anotaciones";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
