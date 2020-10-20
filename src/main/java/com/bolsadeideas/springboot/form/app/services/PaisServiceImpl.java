package com.bolsadeideas.springboot.form.app.services;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/*
Clase que implementa la interface
* */

@Service // Componente de spring
public class PaisServiceImpl implements PaisService{

    private List<Pais> lista; // atributo


    public PaisServiceImpl() { // constructor que inicializa el atributo
        this.lista = Arrays.asList(
                new Pais(1, "Es","España"),
                new Pais(2, "MX","Mexico"),
                new Pais(3, "CL","Chile"),
                new Pais(4, "AR","Argentina"),
                new Pais(5, "PE","Perú"),
                new Pais(6, "CO","Colombia"),
                new Pais(7, "VE","Venezuela")
        );
    }

    /**
     * Metodo que retorna la lista de paises
     * @return la lista de paises
     */
    @Override
    public List<Pais> listar() {
        return lista;
    }

    /**
     * Metodo que busca el pais segun el id que le pasan como argumento.
     * @param id de cada país.
     * @return el pais.
     */
    @Override
    public Pais obtenerPorId(Integer id) { // Metodo que retor
        Pais resultado = null;
        for (Pais pais: this.lista){
            if (pais.getId() == id){
                resultado = pais;
                break;
            }
        }
        return resultado;
    }
}
