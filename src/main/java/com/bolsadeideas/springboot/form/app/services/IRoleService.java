package com.bolsadeideas.springboot.form.app.services;


import com.bolsadeideas.springboot.form.app.models.domain.Role;

import java.util.List;

/* Se crea una interface para la clase que implementa los servicios del Role*/
public interface IRoleService {

    List<Role> listar();

    Role obtenerPorId(Integer id);
}
