package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Usuario {

    private String identificador;

    @NotEmpty(message = "El campo Nombre no debe estar vacio.") // message Para personalizar los mensajes de error en la validacion
    private String nombre;

    @NotEmpty
    private String apellido;

    // 2- anotacion @NotEmpty valida que el campo sea diferente de null y que tenga una longitud, que el campo es requerido.
    @NotEmpty
    @Size(min = 3, max = 8) // Validacion de tamaño solo para String
    private String username; // los nombres de los atributos debe ser igual que los campos de la vista del formulario
    // para que los de la clase sean directamente mapeados a la clase.

    @NotEmpty
    private String password;

    @NotEmpty
    @Email(message = "El campo Email tiene un formato incorrecto.") // Validacion para que la direccion de correo este bien formada
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
