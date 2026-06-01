package com.vittur.vitturapp.dtos.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {
    private String username;
    private String password;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacío")
    @JsonProperty("first_last_name")
    private String first_last_name;
    @JsonProperty("second_last_name")
    private String second_last_name;
    @Email(message = "El email no tiene un formato válido")
    private String email;
    @NotBlank(message = "El teléfono no puede estar vacío")
    @JsonProperty("phone_number")
    private String phone_number;
    @NotNull(message = "El rol no puede estar vacío")
    private Integer role;
}
