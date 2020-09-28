package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.NotEmpty;

public class Usuario {
    // 2- anotacion @NotEmpty valida que el campo sea diferente de null y que tenga una longitud, que el campo es requerido.
    @NotEmpty
    private String username; // los nombres de los atributos debe ser igual que los campos de la vista del formulario
                             // para que los de la clase sean directamente mapeados a la clase.

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

/*  METODOS SETTER AND GETTER **************************************************************************************/

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
}
