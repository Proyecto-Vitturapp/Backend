package com.vittur.vitturapp.services;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String rawPassword) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, usuario.getPassword());
    }
}
