package com.vittur.vitturapp.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionDTO {
    private String matricula;
    private String fechaRevision;
    private Integer kilometrajeActual;
    private String diagnosticoResultado;
    private String fechaProximoMantenimiento;
    private float importe;
}
