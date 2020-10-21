package com.bolsadeideas.springboot.form.app.services;

import com.bolsadeideas.springboot.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/* Esta clase implementa los servicios de la interface IRoleService brinda los servicios del Role*/

@Service
public class RoleServiceImpl implements IRoleService{

    private List<Role> roles;

    public RoleServiceImpl() {
        this.roles = new ArrayList<>();
        roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
        roles.add(new Role(2, "Usuario", "ROLE_USER"));
        roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {
        Role resultado = null;
        for (Role role: this.roles){
            if (role.getId() == id){
                resultado = role;
                break;
            }
        }
        return resultado;
    }
}
