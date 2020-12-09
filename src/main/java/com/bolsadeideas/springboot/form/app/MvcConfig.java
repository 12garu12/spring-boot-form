package com.bolsadeideas.springboot.form.app;

import com.bolsadeideas.springboot.form.app.interceptors.TiempoTranscurridoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("tiempoTranscurridoInterceptor")// Con el nombre del interceptor por si existe mas de uno
    private HandlerInterceptor tiempoTranscurridoInterceptor; // inyeccion de dependencia de la clase TiempoTranscurridoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**"); //se agrega el interceptor y las rutas del controlador para el interceptor. addPathPatterns("/form/**") para las rutas los dos asteriscos indican lo que siga
    }
}
