package com.bolsadeideas.springboot.form.app.editors;

import com.bolsadeideas.springboot.form.app.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/*
Spring utiliza editores de propiedades en gran medida para administrar la conversión entre valores de cadena y tipos de
objetos personalizados ; esto se basa en PropertyEditor de Java Beans para el enlace del editor de propiedades automático
y el enlace del editor de propiedades personalizado .
La infraestructura estándar de JavaBeans detectará automáticamente las clases PropertyEditor si están en el mismo paquete
que la clase que manejan. Además, estos deben tener el mismo nombre que esa clase más el sufijo del editor .
Por ejemplo, si creamos una  clase de modelo CreditCard , entonces deberíamos nombrar la clase de editor  CreditCardEditor.
Esto implementa la lógica empresarial para convertir un string en un objeto.
La clase del editor de propiedades debe extender PropertyEditorSupport e implementar los métodos getAsText () y setAsText ().
Se   llama al método getAsText () cuando se serializa un objeto en un String, mientras que  setAsText ()  se usa para
convertir un String en otro objeto.
ENLACE DEL EDITOR DE PROPIEDADES PERSONALIZADAS.
Si no tenemos la clase de tipo requerida y la clase del editor de propiedades en el mismo paquete o con las convenciones
de nomenclatura esperadas, tendremos que definir un enlace personalizado entre el tipo requerido y el editor de propiedades.
En este escenario de vinculacion del editor de propiedades personalizadas. En esta clase llamada PaisPropertyEditor el cual
edita el String y lo trabaja para ser parte de un objeto.
Dado que Spring no puede detectar el editor de propiedades, necesitaremos un método anotado con  @InitBinder en nuestra
clase Controller que registra el editor.
Luego, podemos vincular la entrada del usuario al objeto en el controlador.
*/


@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private PaisService service;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(idString);
            this.setValue(service.obtenerPorId(id));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
