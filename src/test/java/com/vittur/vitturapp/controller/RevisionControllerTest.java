package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "API_SECRET_KEY=clave_falsa_de_prueba_para_superar_el_arranque_del_contexto",
        "JWT_SECRET_KEY=un_secreto_largo_y_seguro_de_al_menos_256_bits_para_las_pruebas_unitarias_12345"
})
@AutoConfigureMockMvc
public class RevisionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UsuarioRepository usuarioRepository; // Se mantiene para no romper el contexto jpa

    @Test
    @WithMockUser(username = "mecanico@test.com", authorities = {"ROLE_MECANICO"}) // ◄── Inyecta la autoridad exacta
    @DisplayName("GET /api/revisiones debería retornar 200 OK si el usuario tiene rol MECANICO")
    public void testGetRevisionesMecanico() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/revisiones"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "cliente@test.com", authorities = {"ROLE_CLIENTE"}) // ◄── Inyecta la autoridad exacta
    @DisplayName("GET /api/revisiones debería retornar 403 FORBIDDEN si el usuario tiene rol CLIENTE")
    public void testGetRevisionesCliente() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/revisiones"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}