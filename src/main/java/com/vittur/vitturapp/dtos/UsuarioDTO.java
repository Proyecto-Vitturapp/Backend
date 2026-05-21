package com.vittur.vitturapp.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private String username;
    private LocalDate fechaCreacion;
    private String nombre;
    private String apellido;
    private String segundoApellido;
    private String email;
    private Integer rol;
    private String telefono;
}
