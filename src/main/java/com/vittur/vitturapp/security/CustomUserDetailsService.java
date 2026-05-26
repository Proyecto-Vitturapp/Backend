package com.vittur.vitturapp.security;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.repository.UsuarioRepository;
import com.vittur.vitturapp.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String role = "";
        if(usuario.getRol() == 1) {
            role = "MECANICO";
        } else if (usuario.getRol() == 0){
            role = "CLIENTE";
        }
        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(role)
                .build();

    }
}
