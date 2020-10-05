package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> { //  ConstraintValidator<Requerido, String> Anotacion @Requerido, tipo de dato a validar

    /* Metodo Obligatorio a implementar */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        /* PRIMERA FORMA
        if (value == null || value.isEmpty() ||value.isBlank()) return false;*/

        /* SEGUNDA FORMA */
        if (value == null || !StringUtils.hasText(value)) return false; // !StringUtils.hasText(value) pregunta si es
        // distinto de vacio y si tiene texto al evaluar retornara un true si tiene contenido y si no tiene retorna false
        // por eso es la negacion al principio.
        return true;
    }

}
