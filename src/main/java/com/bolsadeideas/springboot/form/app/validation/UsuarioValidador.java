package com.bolsadeideas.springboot.form.app.validation;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component // Anotar con la anotacion @Component para poder inyectarlo en el controlador y validar
public class UsuarioValidador implements Validator {
    // Despues de haber implementado todo el metodo hay que inyectar la clase UsuarioValidador en este caso se inyectara por el tipo concreto por que es una clase de validador y no se va ha tener mas de una implementacion en nuestra
    //  aplicación en caso de que se tenga mas de una implementacion se crea nuestra propia interfaz que sea distinta a Validator distinta a la de Spring y tendriamos que implementar Validator coma nuestra propia interfaz y ahi
    //  podriamos inyectar por nuestra propia interfaz la nuestra no la de Spring por que podrian haber otras clases que la implementen para no tener conflictos.


    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz); // Para dar soporte a la clase Entity. Si es asignable a la clase que
        // se pasa por argumento solamente para soportar y que el objeto que se esta validando corresponda al tipo usuario (clazz)
        // y no otro
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target; // Hacerle un cast y tenemos el usuario para poder validar sus campos

        ValidationUtils.rejectIfEmpty(errors, "nombre", "NotEmpty.usuario.nombre"); // Utilizando la clase helper o de utilidad de Spring(ValidationUtils)
        // Con le metodo (rejectIfEmpty) significa que rechaza la validación con un mensaje de error y esta asociado a un campo
        // 1- Como argumento se pasa el objeto errors. 2- argumento del atributo con el mismo nombre que en la clase el que se quiere validar en un String
        // 3- argumento el mensaje de error que esta en el messages.properties(NotEmpty.usuario.nombre) que apunta al properties

        // Otra al ternativa con un if y a si de esta manera como se muestra en esta clase se puede validar muchos campos de las clases Entity
        if (!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")){ // El metodo matches valida que este correcto.
            errors.rejectValue("identificador", "pattern.usuario.identificador");
            // 1- Argumento ("identificador") con el mismo nombre del atributo
            // 2- argumento("pattern.usuario.identificador") el mensaje de error que esta en el messages.properties que apunta al properties
        }

    }

}
