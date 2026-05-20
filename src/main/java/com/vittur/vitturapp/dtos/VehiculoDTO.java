package com.vittur.vitturapp.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {
    private String matricula;
    private String marca;
    private String modelo;
    private String anyoFabricacion;
    private String tipoVehiculo;
}
