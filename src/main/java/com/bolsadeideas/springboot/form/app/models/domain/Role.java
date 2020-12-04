package com.bolsadeideas.springboot.form.app.models.domain;

public class Role {

    private Integer id;
    private String nombre;
    private String role;

    /* CONSTRUCTOR --------------------------------------------------------------------------------------------------*/
    public Role() {
    }

    public Role(Integer id, String nombre, String role) {
        this.id = id;
        this.nombre = nombre;
        this.role = role;
    }

    /* GETTERS AND SETTERS --------------------------------------------------------------------------------------------*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {// Compara cada objeto de la lista del for each del form.html con la lista de usuarios

        if (this == obj)return true;

        if (!(obj instanceof Role))return false;

        Role role = (Role) obj;

        return this.id != null && this.id.equals(role.getId());
    }
}
