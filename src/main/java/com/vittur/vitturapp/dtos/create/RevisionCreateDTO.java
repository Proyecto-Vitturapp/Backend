package com.vittur.vitturapp.dtos.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevisionCreateDTO {
    @NotNull(message = "La fecha de la revisión no puede ser nula")
    @FutureOrPresent(message = "La fecha de la revisión debe ser una fecha futura o la actual")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaRevision;
    @NotNull(message = "El kilometraje actual no puede ser nulo")
    @PositiveOrZero(message = "El kilometraje actual debe ser un valor positivo o cero")
    private Integer kilometrajeActual;
    @NotBlank(message = "El diagnóstico no puede estar vacío")
    @Size(max = 1000, message = "El diagnóstico no puede tener más de 1000 caracteres")
    private String diagnosticoResultado;
    @NotNull(message = "La fecha del próximo mantenimiento no puede ser nula")
    @FutureOrPresent(message = "La fecha del próximo mantenimiento debe ser una fecha futura o la actual")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaProximoMantenimiento;
    @NotNull(message = "El importe no puede ser nulo")
    @PositiveOrZero(message = "El importe debe ser un valor positivo o cero")
    private float importe;

    @NotBlank(message = "La matrícula no puede estar vacía")
    @Size(max = 15, message = "La matrícula no puede tener más de 15 caracteres")
    private String matricula;
    @NotNull(message = "El identificador del usuario no puede ser nulo")
    private Integer idUsuario;
}
