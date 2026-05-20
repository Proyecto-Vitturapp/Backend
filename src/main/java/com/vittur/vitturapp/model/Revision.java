package com.vittur.vitturapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "revisiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revision {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_revision")
    private Integer idRevision;
    @Column(name = "fecha_revision")
    private String fechaRevision;
    @Column(name = "kilometraje_actual")
    private Integer kilometrajeActual;
    @Column(name = "diagnostico_resultado")
    private String diagnosticoResultado;
    @Column(name = "fecha_proximo_mantenimiento")
    private String fechaProximoMantenimiento;
    @Column(name = "importe")
    private float importe;

    @ManyToOne
    @JoinColumn(name = "matricula")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
