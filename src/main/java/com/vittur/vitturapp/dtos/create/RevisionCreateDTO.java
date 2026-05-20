package com.vittur.vitturapp.dtos.create;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevisionCreateDTO {
    @NotBlank @PastOrPresent
    private String fechaRevision;
    @NotNull @PositiveOrZero
    private Integer kilometrajeActual;
    @NotBlank @Size(max = 1000)
    private String diagnosticoResultado;
    @NotBlank @PastOrPresent
    private String fechaProximoMantenimiento;
    @NotNull @PositiveOrZero
    private float importe;

    @NotBlank @Size(max = 15)
    private String matricula;
    @NotNull
    private Integer idUsuario;
}
