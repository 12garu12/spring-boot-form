package com.bolsadeideas.springboot.form.app.models.domain;

//import javax.validation.constraints.NotNull;

public class Pais {

    /* Para validar cualquier campo es importante tener el en cuenta el tipo de dato para String @NotEmpty @NotBlank
    * para Integer @NotNull y tambien tener en cuenta cambiar los datos en el select del formulario y los datos en la
    * vista resultado*/
    //@NotNull
    private Integer id;

    private String codigo;

    private String nombre;



    /*************************************************************************************************************/

    public Pais() {
    }

    public Pais(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /*************************************************************************************************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
