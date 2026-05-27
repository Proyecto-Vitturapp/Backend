package com.vittur.vitturapp.services;

import com.vittur.vitturapp.dtos.JwtResponseDTO;
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
    private final JwtService jwtService;

    public JwtResponseDTO authenticate(String username, String rawPassword) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario == null) {
            return null;
        }
        if (!passwordEncoder.matches(rawPassword, usuario.getPassword())) {
            return null;
        }
        String token = jwtService.generateToken(usuario.getUsername(), usuario.getRol());
        return new JwtResponseDTO(token, usuario.getUsername(), usuario.getRol());
    }
}
