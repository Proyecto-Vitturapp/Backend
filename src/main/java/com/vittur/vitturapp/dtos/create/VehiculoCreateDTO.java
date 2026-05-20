package com.vittur.vitturapp.dtos.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoCreateDTO {
    @NotBlank @Size(max = 15)
    private String matricula;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank @PastOrPresent
    private String anyoFabricacion;
    @NotBlank
    private String tipoVehiculo;
}
