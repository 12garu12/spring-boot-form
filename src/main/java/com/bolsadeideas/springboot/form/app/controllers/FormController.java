package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("usuario")
/* Con la anotacion @SessionAttributes("nombrDelObjetoDeLaClaseConElCualSePasaAlaVista") para mantener los datos del objeto Entity algun atributo que no esta mapeado al formulario que no es un
* campo pero puede estar almacenado en algun lugar como lo es el campo identificador de la clase usuario, o por ejemplo en una base de datos, por defecto cuando un atributo
* de este objeto mapeado  no esta como campo en el formulario se pierde queda en null, por que finalmente lo que se envia son los campos del formulario y se llenan en el objeto
* usuario  pero todo atributo que no este en el formulario quedara en null, y si queremos actualizar ese valor se actualiza en null y lo perdemos en la base de datos y no es la
* idea, la idea es mantener todos los datos.
* Con la anotacion @SessionAttributes("nombrDelObjetoDeLaClaseConElCualSePasaAlaVista") esta anotacion guardara el valor del atributo en una sesion HTTP y todos los datos que
* tenga independientes si estan o no en el formulario como campo se mantienen de forma persistente entre el formulario y el procesar cuando el formulario envia los datos al
* controlador para que los procese y los datos no se van a perder y si se cambia un dato en el formulario se va actualizar  y se refleja el cambio, pero cuando finaliza el
* proceso por ejemplo cuando lo guardamos en la base de datos, tenemos que limpiar atravez de un objteto de la interface SessionStatus pasado como parametro del metodo.
* en el metodo procesar status.setComplete() completa el proceso y de forma automatica se elimina el objeto Usuario de la sesion. De esta forma mantendra todos los atributos
* que no estan en el formulario y solamente va actualizar aquellos campos que se ingresan en el formulario o que han sido modificados, muy importante por ejemplo para los
* IDs que son propios del formularios he internos o cualquier otra informacion
*  */
public class FormController {

    /**
     * Metodo para la vista del formulario en una vista HTML.
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada form.html.
     */
    @GetMapping("/form")
    public String form(Model model){ // Se agrego el objeto usuario en los parametros por que da un error por el campo th:value="${usuario.username}" en el formulario al no reconocer usuario daba un error en el servidor.
        Usuario usuario = new Usuario(); // una forma es crear el objeto manualmente para pasar los datos del objeto a la vista form
        usuario.setNombre("Jhon");
        usuario.setApellido("Doe");
        usuario.setIdentificador("123.456.789-k"); // Como este dato no esta mapeado en el formulario saldra null y se perdera la infomacion
        model.addAttribute("titulo", "Formulario Usuarios");
        model.addAttribute("usuario", usuario); // para pasar datos a la vista con la anotación @ModelAtribute con el nombre user.
        return "form";
    }

    /**
     * Metodo para la respuesta que procesa los datos que llegan del formulario el formulario
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada resultado.html con el resultado del procesamiento de los datos.
     */
    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status){  /*1- Anotación @Valid para validar los datos mapeados hacia la clase usuario
    3- interface BindingResult contiene los mensajes de error de la validacion en caso de que hayan errores va unido a @Valid justo despues de esta anotación como regla
    va de primero en los argumentos el objeto validado y segundo el BindingResul
    4- @ModelAttribute para cambiar el nombre con que pasamos los datos a la vista*/


        model.addAttribute("titulo", "Resultado del formulario"); // con el objeto model utilizando clave valor pasamos datos a la vista resultado.html

        /* Para automatizar los mensajes de error que los maneje thymeleaf y Spring Framework de forma automatica he implicita*/
        if(result.hasErrors()){
             return "form";
        }

        model.addAttribute("usuario", usuario);
        status.setComplete(); // completa el proceso y de forma automatica se elimina el objeto Usuario de la sesion
        return "resultado";
    }

}
