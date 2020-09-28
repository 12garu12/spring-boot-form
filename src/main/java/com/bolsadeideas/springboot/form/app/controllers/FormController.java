package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

    /**
     * Metodo para la vista del formulario en una vista HTML.
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada form.html.
     */
    @GetMapping("/form")
    public String form(Usuario usuario, Model model){ // Se agrego el objeto usuario en los parametros por que da un error por el campo th:value="${usuario.username}" en el formulario al no reconocer usuario daba un error en el servidor.
        model.addAttribute("titulo", "Formulario Usuarios");
        return "form";
    }

    /**
     * Metodo para la respuesta que procesa los datos que llegan del formulario el formulario
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada resultado.html con el resultado del procesamiento de los datos.
     */
    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model){  /*1- Anotación @Valid para validar los datos mapeados hacia la clase usuario
    3- interface BindingResult contiene los mensajes de error de la validacion en caso de que hayan errores va unido a @Valid justo despues de esta anotación como regla
    va de primero en los argumentos el objeto validado y segundo el BindingResul */

        model.addAttribute("titulo", "Resultado del formulario"); // con el objeto model utilizando clave valor pasamos datos a la vista resultado.html

        if(result.hasErrors()){ // validacion
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{    // Para obtener la lista de errores hay que iterarla para mostrar los mensajes de error por cada campo del formulario
               errores.put(err.getField(), "El campo ".concat(err.getField().concat(" "). concat(err.getDefaultMessage()))); // (getField = obtiene el nombre del campo) (getDefaultMessage = obtiene el mensaje)
            });
            model.addAttribute("error", errores); //pasar el objeto Map, que contiene la lista de errores a la vista para el Usuario.
             return "form";
        }

        model.addAttribute("usuario", usuario);

        return "resultado";
    }

}
