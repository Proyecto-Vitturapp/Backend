package com.vittur.vitturapp.dtos.create;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String plate;
    @NotBlank(message = "La marca no puede estar vacía")
    private String brand;
    @NotBlank(message = "El modelo no puede estar vacío")
    private String model;
    @NotBlank(message = "El año de fabricación no puede estar vacío")
    @Pattern(regexp = "^-?\\d+$", message = "El campo debe ser un número entero válido.")
    @JsonProperty("fabrication_year")
    private String fabrication_year;
    @NotBlank(message = "El tipo de vehículo no puede estar vacío")
    @JsonProperty("vehicle_type")
    private String vehicle_type;
    @JsonProperty("next_review_date")
    private LocalDate next_review_date;
}