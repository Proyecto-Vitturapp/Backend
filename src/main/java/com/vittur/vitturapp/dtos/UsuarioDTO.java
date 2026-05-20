package com.vittur.vitturapp.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String username;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String telefono;
}
