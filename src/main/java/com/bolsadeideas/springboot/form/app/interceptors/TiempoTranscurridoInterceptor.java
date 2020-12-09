package com.bolsadeideas.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


@Component("tiempoTranscurridoInterceptor") // Con el nombre para identificar este interceptor
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class); // el logger sirve para poder registrar algun evento en el log o en la traza de la aplicación

    /**
     * Metodo interceptor para ejecutar una tarea antes de la petición HTTP.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equalsIgnoreCase("post")){ // para aplicar este metodo solo al metodo de tipo get en el controlador obtenemos el metodo con getMethod() y con equalsIgnoreCase("post") colocamos el nombre del tipo de metodo en el controlador
            return true;
        }

        if(handler instanceof HandlerMethod){ // con la sentencia if se le pregunta si es una instancia de alguna clase
            HandlerMethod method = (HandlerMethod) handler; // se le hace un cast por que el objeto handler es de tipo Object.
            LOGGER.info("Es un metodo del controlador: " + method.getMethod().getName()); // para saber el nombre del metodo del controlador
        }

        LOGGER.info("TiempoTranscurridoInterceptor: preHandle() entrando ... ");
        LOGGER.info("Interceptando: " + handler); // para ver alguna informacion en la consola
        long tiempoInicio = System.currentTimeMillis(); // tiempp del sistema actual.
        request.setAttribute("tiempoInicio", tiempoInicio); // El metodo setAttribute trabaja como el ModelAttribute clave valor, recibe como argumento un String y guarda un tipo Object.

        Random random = new Random(); // Objeto de la clase random para llamar los metodos de la clase random
        Integer demora = random.nextInt(500); // para emular el tiempo de demora, el argumento crea un numero aleatorio entre 0 y 500;
        Thread.sleep(demora); //Hace que el subproceso que se está ejecutando se suspenda (cese temporalmente la ejecución) durante el número especificado de milisegundos



        response.sendRedirect(request.getContextPath().concat("/login")); /* con el Objeto response para cambiar la ruta y el metodo sendRedirect().
        siempre la ruta base tiene que partir del contexto del path base o la ruta base de nuestra aplicación request.getContextPath()
        por lo general el getContextPath() deberia ir vacio ya que no tiene un nombre de proyecto en la ruta pero lo podria tener
        se podría indicar en la configuración de spring boot cual es nuestra ruta base aunque en la mayoria de los casos no es necesario
        es en caso de que lo tengamos o si publicamos por ejemplo en un servidor externo el context path seria el nombre del war que estemos
        publicando por ejemplo en un tomcat externo o en cualquier servidor de aplicaciones. En este ejemplo la ruta "/login" no exite es para el
        ejemplo la idea es para redirigir a una pagina por ejemplo el login o donde queremos manejar esta respuesta que fallo en la validación.
        */

        return false; /* Cuando un contexto de validacion falla por ejemplo un loggin de usuario preguntando si el usuario esta
        autenticado si el return es true se deja pasar al controlador para que invoque los metodos handler sin ningun problema
        de lo contrario si no tiene sesion retornamos un false de esta manera los metodos del controlador no se invocan y en
        ningun momento se van a ejecutar  por que con false no continua con la ejecución normal el flujo de la aplicación
        y queda hasta ahí y se maneja la respuesta en el interceptor por ejemplo redirigir a otra ruta


        */


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (request.getMethod().equalsIgnoreCase("post")){ // para aplicar este metodo solo al metodo de tipo get en el controlador obtenemos el metodo con getMethod() y con equalsIgnoreCase("post") colocamos el nombre del tipo de metodo en el controlador
            return; // este metodo es de tipo void
        }

        long TiempoFin = System.currentTimeMillis(); // tiempo del sistema actual.
        long tiempoInicio = (Long) request.getAttribute("tiempoInicio"); // Obtiene el valor del objeto guardado y como es de tipo Object se le hace un cast.
        long tiempotranscurrido = TiempoFin - tiempoInicio; // Para obtener el tiempo transcurrido restamos el tiempo final menos el inicial.

        if (handler instanceof HandlerMethod && modelAndView != null){
            modelAndView.addObject("tiempoTranscurrido", tiempotranscurrido);
            // modelAndView sirve para pasar datos a la vista con clave valor. Agrega un atributo al modelo.
            //Parámetros:
            //attributeName: nombre del objeto que se agregará al modelo (nunca nulo)
            //atributoValue: objeto para agregar al modelo (puede ser nulo)
            //Ver también:
            //ModelMap.addAttribute (Cadena, Objeto), getModelMap ().
        }

        LOGGER.info("Tiempo transcurrido: " + tiempotranscurrido + " milisegundos");
        LOGGER.info("TiempoTranscurridoInterceptor postHandle() saliendo ... ");

    }
}
