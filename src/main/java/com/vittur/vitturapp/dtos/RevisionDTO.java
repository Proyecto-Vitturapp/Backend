package com.vittur.vitturapp.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionDTO {
    private Integer idRevision;
    private String matricula;
    private Integer idCliente;
    private LocalDate fechaRevision;
    private Integer kilometrajeActual;
    private String diagnosticoResultado;
    private float importe;
}
