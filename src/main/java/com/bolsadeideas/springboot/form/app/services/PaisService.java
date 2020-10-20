package com.bolsadeideas.springboot.form.app.services;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;

import java.util.List;


/*Se crea un package service y una interface mas la clase que la implementa*/
public interface PaisService {

    public List<Pais> listar();

    public Pais obtenerPorId(Integer id);

}
