package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    /**
     * Metodo para la vista del formulario en una vista HTML.
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada form.html.
     */
    @GetMapping("/form") // Anotacion @GetMapping metodo de solicitud HTTP con el metodo GET.
    public String form(Model model){
        model.addAttribute("titulo", "Formulario Usuarios"); // pasa con el nombre de la variable titulo un string a la vista form.html
        return "form";
    }

    /**
     * Metodo para la respuesta que procesa los datos que llegan del formulario el formulario
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @param username variable que llega del formulario con el nombre del usuario.
     * @param password variable que llega del formulario con el password del usuario.
     * @param email variable que llega del formulario con el email del usuario.
     * @return retorna una vista llamada resultado.html con el resultado del procesamiento de los datos.
     */
    @PostMapping("/form")// Anotacion @PostMapping se usa para manejar el tipo de método de solicitud POST.
    public String procesar(Model model, @RequestParam (name = "username") String username, //  @RequestParam para extraer el parámetro de consulta username.
                                        @RequestParam String password, //  @RequestParam para extraer el parámetro de consulta password.
                                        @RequestParam String email){  //  @RequestParam para extraer el parámetro de consulta email estos tres parametros provienen del formulario.

        model.addAttribute("titulo", "Resultado del formulario"); // con el objeto model utilizando clave valor pasamos datos a la vista resultado.html
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);

        return "resultado";
    }

}
