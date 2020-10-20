package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired // Inyectamos nuestra clase validadora.
    private UsuarioValidador validador;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PaisPropertyEditor paisEditor;

    /* Otra alternativa en vez de utilizat de forma explicita en el codigo el validate y lo vamos a automatizar para que se valide con la anotacion @Valid de forma transparente si hacer nada en el
    procesar osea en el metodo handler (@PostMapping("/form")) para eso tenemos que implementar y registrar este validador en el InitBinder cuando se inicializa el proceso de validacion y el proceso
    de pasar los datos al objeto usuario pero eso se hace detras de escena por debajo lo hace el framework cuando se envian los datos del formulario el controlador los recibe y los puebla al objeto usuario
    y tambien los valida eso seria el inicializar el InitBinder */
    @InitBinder
    public void initBinder(WebDataBinder binder) { // WebDataBinder un objeto propio de Spring
        binder.addValidators(validador); /* le pasamos el atributo validador de UsuarioValidador y lo que hace es validar de forma transparente
        Con el metodo addValidators lo que hace es agregar un nuevo validador al Stack el initBinder esta anotacion es un evento del ciclo de
        vida del controlador cuando se inicializa el binder. De esta manera no se pierden las anotaciones validadoras que existen en el Entity
        Usuario, este metodo tiene la ventaja de que desacopla del metodo handler del controlador lo que tenemos en la clase UsuarioValidador
        siendo una forma mas implicita */

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // setLenient es la indulgencia que define si el analizador que va ha realizar un analisis de la fecha con este formato, si este analizador es estricto o tolerante al interpretar
        // este patron por ejemplo si escrigimos mal el formato el de forma automatica lo va ha convertir y no va haber ningun error, pero lo ideal es que sea estricto que no sea tolerante por eso lo dejamos en false
        // para evitar que no coloquen fechas en un formato que no sea el correcto ya que el analizador lo podria interpretar de forma distinta a como esperamos la fecha, para evitar la ambiguedad con la fecha, si la
        // fecha no esta correcta lanzara un error, esto se esta manejando con validacion en messages.properties
        binder.registerCustomEditor(Date.class, "fechaNacimiento",new CustomDateEditor(dateFormat, true)); // false que no permite vacios, El metodo registerCustomEditor
        // tambien se puede mapear de una forma mas especifica a un campo especifico como el fechaNacimiento de la clase usuario

        // Tenemos que registrar otro customEditor para la clase propia que creamos llamada NombreMayusculaEditor con
        // 2 parametros es global para todos los campos String y con 3 parametros especificamos a que clase se va ha aplicar
        binder.registerCustomEditor(String.class, "nombre",new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "apellido",new NombreMayusculaEditor());

        /*Dado que Spring no puede detectar el editor de propiedades, necesitaremos un método anotado con  @InitBinder
        en nuestra clase Controller que registra el editor:*/
        binder.registerCustomEditor(Pais.class, "pais", paisEditor);

    }

    /**
     * Metodo para utilizaren un campo del formulario con una lista desplegable con nombres de paises
     * @return una lista de varios paises.
     */
    @ModelAttribute("listaPaises") // El argumento es el nombre con el cual se pasa a la vista lo que retorna
    public List<Pais> listaPaises(){ /* lista para el campo desplegable en el formulario*/
        return paisService.listar();
    }



    /**
     * Metodo para utilizaren un campo del formulario con una lista desplegable con nombres de paises
     * @return una lista de varios paises.
     */
    @ModelAttribute("paises") // El argumento es el nombre con el cual se pasa a la vista lo que retorna
    public List<String> paises(){ /* lista para el campo desplegable en el formulario*/
        return Arrays.asList("España", "Mexico", "Chile", "Argentina","Perú", "Colombia", "Venezuela");
    }

    /**
     * Metodo para utilizar en un campo del formulario con una lista desplegable con nombres de paises
     * @return una lista de varios paises.
     */
    @ModelAttribute("paisesMap") // El argumento es el nombre con el cual se pasa a la vista lo que retorna
    public Map<String, String > paisesMap(){ /* lista para el campo desplegable en el formulario*/
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("MX", "Mexico");
        paises.put("CL", "Chile");
        paises.put("AR", "Argentina");
        paises.put("PE", "Perú");
        paises.put("CO", "Colombia");
        paises.put("VE", "Venezuela");
        return paises;
    }

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
        usuario.setIdentificador("123.456.789-K"); // Como este dato no esta mapeado en el formulario saldra null y se perdera la infomacion
        model.addAttribute("titulo", "Formulario Usuarios");
        model.addAttribute("usuario", usuario); // para pasar datos a la vista con la anotación @ModelAtribute con el nombre user.
        return "form";
    }

    /**
     * Metodo para la respuesta que procesa los datos que llegan del formulario el formulario.
     * @param model objeto de la interfaz model para pasa datos a la vista.
     * @return retorna una vista llamada resultado.html con el resultado del procesamiento de los datos.
     */
    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status){  /*1- Anotación @Valid para validar los datos mapeados hacia la clase usuario
    3- interface BindingResult contiene los mensajes de error de la validacion en caso de que hayan errores va unido a @Valid justo despues de esta anotación como regla
    va de primero en los argumentos el objeto validado y segundo el BindingResul
    4- @ModelAttribute para cambiar el nombre con que pasamos los datos a la vista*/

//        validador.validate(usuario, result); // la inyeccion hace el llamado al metodo validate de la clase UsuarioValidador y pasa el target(objeto usuario)
        // pasamos para validar el objeto y el objeto de BindingResult que es el objeto que pasa los errores


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
