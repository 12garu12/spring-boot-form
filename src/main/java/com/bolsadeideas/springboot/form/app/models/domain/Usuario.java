package com.bolsadeideas.springboot.form.app.models.domain;

import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;
import com.bolsadeideas.springboot.form.app.validation.Requerido;
//import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
//import javax.validation.constraints.Pattern;


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

    //@NotEmpty
    @Requerido
    private String apellido;

    // 2- anotacion @NotEmpty valida que el campo sea diferente de null y que tenga una longitud, que el campo es requerido.
    @NotBlank
    // Para la validacion que el campo no tenga espacios en blanco no es recomenda usarlo junto con la anotacion @NotEmpty por que las validara a todas solo con @NOtBlank esta bien
    @Size(min = 3, max = 8) // Validacion de tamaño solo para String
    private String username; // los nombres de los atributos debe ser igual que los campos de la vista del formulario
    // para que los de la clase sean directamente mapeados a la clase.

    @NotEmpty  /* Las anotaciones @NotEmpty y @NotBlank son para Strings */
    private String password;

    @Requerido
    @Email
    private String email;

    @NotNull  /* Esta anotacion es la mas indicdda para validacion de un campo qu sea del tipo entero  Se utiliza para validar objetos*/
    @Min(5) // Valida que el valor minimo del numero entero sea 5
    @Max(5000)  // Valida que el valor maximo del numero entero sea 5000
    private Integer cuenta;

    @NotNull
    @Past // Para validar que la fecha que se introduce en el formulario sea una fecha pasada a la actual.
    //@Future  // Para validar que la fecha que se introduce en el formulario sea una fecha futura a la actual.
    //@DateTimeFormat(pattern = "yyyy-MM-dd") // Se comento para validar con CustomDateEditor en el controller
    private Date fechaNacimiento;

    @NotNull // ahora se valida el objeto completo
    private Pais pais; // Para lista select o desplegable para el formulario con formato en la vista

    @NotEmpty // valida que la lista no este vacia
    private List<Role> roles; //  La idea es que el usuario pueda tener roles cuando se registra o en el formulario
    // pueda seleccionar los roles a los cuales puda pertenecer el usuario la idea es que pueda tener mas de un rol

    // Este campo no se valida por ser opcional.
    private Boolean habilitar; /* para Checkbox habilitddo o desabilitado por ejemplo para marcar una opción y este quede
    registrado en el atributo como si o no a true o false por ejemplo para habilitar un usuario o deshabilitar o si un
    usuario se quiere registrar con una cuenta y quiere recibir email o noticias de la empresa */

    @NotEmpty
    private String genero; // para la opcion radio button en el formulario con el genero masculino femenino implementado en el controlador con @ModelAttribute

    private String valorSecreto; // para envio de datos que el usuario no puede ver en el formulario


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

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Boolean habilitar) {
        this.habilitar = habilitar;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getValorSecreto() {
        return valorSecreto;
    }

    public void setValorSecreto(String valorSecreto) {
        this.valorSecreto = valorSecreto;
    }
}
