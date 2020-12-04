package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/* Para validacion personalizada utilizando anotaciones como por ejemplo la anotacion @NotEmpty pero personalizado por ejmplo
 * para validar alguna expresion personalizada pero sin utilizar pattern algo mas programatico o validar cualquier campo similar
 * como se hizo en la clase UsuarioValidador pero con anotaciones en un los atributos igual que @NotBlank, @NotEmpty, @Email
 * 1. Crear la anotacion como se ve aqui.
 * 2. Crear la clase validadora en este caso es la clase IdentificadorRegexValidador
 * 3. Enlazar la anotacion con la clase creada IdentificadorRegexValidador*/
@Constraint(validatedBy = IdentificadorRegexValidador.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IdentificadorRegex {
    /* Estos tres metodos fueron copiados de la anotacion @NotEmpty*/
    String message() default "Identificador inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
