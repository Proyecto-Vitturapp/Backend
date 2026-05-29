package com.vittur.vitturapp.services;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Debería retornar el usuario mapeado correctamente cuando existe en la base de datos")
    public void testObtenerUsuarioExistente() {
        Usuario usuario = new Usuario();
        usuario.setUsername("mecanico1test");
        usuario.setRol(1);
        usuario.setPassword("testpassword");

        Mockito.when(usuarioRepository.findByUsername("mecanico1test"))
                .thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.getUsuarioByUsername("mecanico1test");

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("mecanico1test", resultado.getUsername());

        Mockito.verify(usuarioRepository, Mockito.times(1)).findByUsername("mecanico1test");
    }

    @Test
    @DisplayName("Debería lanzar una excepción UsernameNotFoundException cuando el usuario no existe en la base de datos")
    public void testObtenerUsuarioNoExistente() {
        Mockito.when(usuarioRepository.findByUsername("usuarioNoExistente"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            usuarioService.getUsuarioByUsername("usuarioNoExistente");
        });

        Mockito.verify(usuarioRepository, Mockito.times(1)).findByUsername("usuarioNoExistente");
    }
}