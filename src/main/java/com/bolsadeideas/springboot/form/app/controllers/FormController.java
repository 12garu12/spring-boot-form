package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    /**
     * Metodo para la vista del formulario en una vista HTML.
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada form.html.
     */
    @GetMapping("/form") // Anotacion @GetMapping metodo de solicitud HTTP con el metodo GET.
    public String form(Model model){
        return "form";
    }

    /**
     * Metodo para la respuesta del procesar el formulario.
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada resultado.html.
     */
    @PostMapping("/form")// Anotacion @PostMapping se usa para manejar el tipo de método de solicitud POST.
    public String procesar(Model model){
        return "resultado";
    }

}
