package com.vittur.vitturapp.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {
    private String matricula;
    private String marca;
    private String modelo;
    private String anyoFabricacion;
    private String tipoVehiculo;
    private LocalDate fechaProximoMantenimiento;
    private Integer status;
}
