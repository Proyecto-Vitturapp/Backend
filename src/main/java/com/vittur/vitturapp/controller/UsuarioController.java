package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.UsuarioDTO;
import com.vittur.vitturapp.dtos.VehiculoDTO;
import com.vittur.vitturapp.dtos.create.UsuarioCreateDTO;
import com.vittur.vitturapp.model.Usuario;
import com.vittur.vitturapp.model.Vehiculo;
import com.vittur.vitturapp.services.UsuarioService;
import com.vittur.vitturapp.services.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users/all")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios(){
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for (Usuario usu : usuarios) {
                usuariosDTO.add(toUsuarioDTO(usu));
            }
            return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id){
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return new ResponseEntity<>(toUsuarioDTO(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/all/total")
    public ResponseEntity<Long> getTotalUsuarios(){
        try {
            long total = usuarioService.getTotalUsuarios();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicle/{matricula}/users")
    public ResponseEntity<List<UsuarioDTO>> getUsuariosByMatricula(@PathVariable String matricula){
        try {
            List<Usuario> usuarios = usuarioService.getUsuariosByMatricula(matricula);
            List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for (Usuario usu : usuarios) {
                usuariosDTO.add(toUsuarioDTO(usu));
            }
            return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> addUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        try{
            Usuario usuario = new Usuario();
            createUsuario(usuarioCreateDTO, usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        try {
            Usuario currentUsuario = usuarioService.getUsuarioById(id);
            updateUsuarioFields(usuarioCreateDTO, currentUsuario);
            return new ResponseEntity<>(toUsuarioDTO(currentUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        try {
            usuarioService.deleteUsuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUser_id(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername().toLowerCase());
        usuarioDTO.setCreation_date(usuario.getFechaAlta());
        usuarioDTO.setName(usuario.getNombre());
        usuarioDTO.setFirst_last_name(usuario.getApellido());
        usuarioDTO.setSecond_last_name(usuario.getSegundoApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRole(usuario.getRol());
        usuarioDTO.setPhone_number(usuario.getTelefono());
        return usuarioDTO;
    }

    private void createUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO, Usuario usuario) {
        usuario.setUsername(usuarioCreateDTO.getUsername().toLowerCase());
        usuario.setPassword(usuarioCreateDTO.getPassword());
        usuario.setNombre(usuarioCreateDTO.getName());
        usuario.setApellido(usuarioCreateDTO.getFirst_last_name());
        usuario.setSegundoApellido(usuarioCreateDTO.getSecond_last_name());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setTelefono(usuarioCreateDTO.getPhone_number());
        usuario.setRol(usuarioCreateDTO.getRole());
        usuarioService.save(usuario);
    }

    private void updateUsuarioFields(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO, Usuario usuario) {
        usuario.setUsername(usuarioCreateDTO.getUsername().toLowerCase());
        if (usuarioCreateDTO.getPassword() != null && !usuarioCreateDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioCreateDTO.getPassword()));
        }
        usuario.setNombre(usuarioCreateDTO.getName());
        usuario.setApellido(usuarioCreateDTO.getFirst_last_name());
        usuario.setSegundoApellido(usuarioCreateDTO.getSecond_last_name());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setTelefono(usuarioCreateDTO.getPhone_number());
        usuario.setRol(usuarioCreateDTO.getRole());
        usuarioService.update(usuario);
    }
}
