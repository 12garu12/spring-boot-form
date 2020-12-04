package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/* Esta clase es para utilizarla con la anotacion que creamos hay que implementar el metodo isValid */
public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {
    // Se implementa la interface ConstraintValidator (<AnotacionQueCreamos, TipoDeDatoDelCampoAValidar>)

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) { // value es el atributo que se va ha validar
        if (value.matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
            return true;/* Si esta bien validado*/
        }
        return false; // Retorna un boolean segun lo que se valida si esta bien o mal
    }
}
