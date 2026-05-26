package com.vittur.vitturapp.services;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.repository.UsuarioRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public void deleteUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> getUsuariosByMatricula(String matricula){
        return usuarioRepository.findByVehiculosMatricula(matricula);
    }
}
