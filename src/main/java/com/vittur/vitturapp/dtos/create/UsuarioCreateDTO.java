package com.vittur.vitturapp.dtos.create;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    private String segundoApellido;
    @Email(message = "El email no tiene un formato válido")
    private String email;
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
}
