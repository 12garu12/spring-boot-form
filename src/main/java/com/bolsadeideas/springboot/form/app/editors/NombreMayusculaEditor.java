package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;



/* Para implementar un propio filtro custom property para convertir a mayúscula se crea un package dentro del package
principal por ejemplo en este proyecto es llamado editors y una clase llamada NombreMayusculaEditor que hereda de la
clase PropertyEditorSupport del package java.beans
*/

public class NombreMayusculaEditor extends PropertyEditorSupport {

    /* Este metodo esta en override Methods.... click derecho > Generate > Override Methods > setAsText()
    Recibe el valor de cada campo del formulario que este mapeado o asignado a este editor */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase().trim()); // aqui convertimos el texto recibido
    }
}
