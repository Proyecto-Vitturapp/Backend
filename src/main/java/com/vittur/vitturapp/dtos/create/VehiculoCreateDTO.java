package com.vittur.vitturapp.dtos.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoCreateDTO {
    @NotBlank(message = "La matrícula no puede estar vacía")
    @Size(max = 15, message = "La matrícula no puede tener más de 15 caracteres")
    private String matricula;
    @NotBlank(message = "La marca no puede estar vacía")
    private String marca;
    @NotBlank(message = "El modelo no puede estar vacío")
    private String modelo;
    @NotBlank(message = "El año de fabricación no puede estar vacío")
    @Pattern(regexp = "^-?\\d+$", message = "El campo debe ser un número entero válido.")
    private String anyoFabricacion;
    @NotBlank(message = "El tipo de vehículo no puede estar vacío")
    private String tipoVehiculo;
    private LocalDate fechaProximoMantenimiento;
}