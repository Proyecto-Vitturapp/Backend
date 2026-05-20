package com.vittur.vitturapp.dtos.create;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {
    @NotBlank
    private String username;
    @Size(min = 8)
    private String password;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Email
    private String email;
    @NotBlank
    private String telefono;
}
