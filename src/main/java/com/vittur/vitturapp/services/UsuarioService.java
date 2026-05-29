package com.vittur.vitturapp.services;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.repository.UsuarioRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public long getTotalUsuarios(){
        return usuarioRepository.count();
    }

    public void save(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario getUsuarioByUsername(String username){
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public void deleteUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> getUsuariosByMatricula(String matricula){
        return usuarioRepository.findByVehiculosMatricula(matricula);
    }




}
