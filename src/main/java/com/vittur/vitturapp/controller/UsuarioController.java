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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/usuarios")
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

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id){
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return new ResponseEntity<>(toUsuarioDTO(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios/total")
    public ResponseEntity<Long> getTotalUsuarios(){
        try {
            long total = usuarioService.getTotalUsuarios();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehiculos/{matricula}/usuarios")
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

    @PostMapping("/usuarios")
    public ResponseEntity<?> addUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        try{
            Usuario usuario = new Usuario();
            setUsuario(usuarioCreateDTO, usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        try {
            Usuario currentUsuario = usuarioService.getUsuarioById(id);
            setUsuario(usuarioCreateDTO, currentUsuario);
            return new ResponseEntity<>(toUsuarioDTO(currentUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
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
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername().toLowerCase());
        usuarioDTO.setFechaCreacion(usuario.getFechaAlta());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setSegundoApellido(usuario.getSegundoApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRol(usuario.getRol());
        usuarioDTO.setTelefono(usuario.getTelefono());
        return usuarioDTO;
    }

    private void setUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO, Usuario usuario) {
        usuario.setUsername(usuarioCreateDTO.getUsername().toLowerCase());
        usuario.setPassword(usuarioCreateDTO.getPassword());
        usuario.setNombre(usuarioCreateDTO.getNombre());
        usuario.setApellido(usuarioCreateDTO.getApellido());
        usuario.setSegundoApellido(usuarioCreateDTO.getSegundoApellido());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setTelefono(usuarioCreateDTO.getTelefono());
        usuarioService.save(usuario);
    }
}
