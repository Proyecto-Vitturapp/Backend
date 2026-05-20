//TODO: Finish routing in UsuarioController and create VehiculoController
package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.UsuarioDTO;
import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> getAllUsuarios(){
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        for (Usuario usu : usuarios) {
            usuariosDTO.add(toUsuarioDTO(usu));
        }
        return usuariosDTO;
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        return toUsuarioDTO(usuario);
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRol(usuario.getRol());
        usuarioDTO.setTelefono(usuario.getTelefono());
        return usuarioDTO;
    }
}
