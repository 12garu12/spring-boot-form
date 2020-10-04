package com.bolsadeideas.springboot.form.app.models.domain;

import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {

    /* Utilizando expresiones regulares y la anotacion @Pattern para validacion
    * [0-9] en un rango de 0 a 9
    * {2} de 2 caracteres
    * [.] seguido de un punto
    * [\\d] cualquier digito de 0 a9
    * {3} de 3 digitos
    * [-] seguido de un guion
    * [A-Z] un caracter de la A a la Z en mayuscula
    * */

    @IdentificadorRegex /* Anotacion personalizada por nosotros mismos*/
    private String identificador;

    // message Para personalizar los mensajes de error en la validacion tambien con el nombre de la anotacion en un
    // archivo properties y prima por encima del atributo message de la anotacion.
    // @NotEmpty(message = "El campo Nombre no debe estar vacio.") Se comento por que se va ha validar en la clase validation.
    private String nombre;

    @NotEmpty
    private String apellido;

    // 2- anotacion @NotEmpty valida que el campo sea diferente de null y que tenga una longitud, que el campo es requerido.
    @NotBlank // Para la validacion que el campo no tenga espacios en blanco no es recomenda usarlo junto con la anotacion @NotEmpty por que las validara a todas solo con @NOtBlank esta bien
    @Size(min = 3, max = 8) // Validacion de tamaño solo para String
    private String username; // los nombres de los atributos debe ser igual que los campos de la vista del formulario
    // para que los de la clase sean directamente mapeados a la clase.

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

/*  METODOS SETTER AND GETTER **************************************************************************************/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
