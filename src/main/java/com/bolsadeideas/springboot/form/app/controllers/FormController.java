package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
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
        model.addAttribute("titulo", "Formulario Usuarios"); // pasa con el nombre de la variable titulo un string a la vista form.html
        return "form";
    }

    /**
     * Metodo para la respuesta que procesa los datos que llegan del formulario el formulario
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada resultado.html con el resultado del procesamiento de los datos.
     */
    @PostMapping("/form")// Anotacion @PostMapping se usa para manejar el tipo de método de solicitud POST.
    public String procesar(Usuario usuario, Model model){  // para automatizar mas se puede pasar el objeto usuario como
                                                           // parametro y este a su vez mapea y llena  los atributos de la clase usuario automaticamente.

        // Se pasa el objeto de la clase usuario a la vista resultado.html
        model.addAttribute("titulo", "Resultado del formulario"); // con el objeto model utilizando clave valor pasamos datos a la vista resultado.html
        model.addAttribute("usuario", usuario);

        return "resultado";
    }

}
