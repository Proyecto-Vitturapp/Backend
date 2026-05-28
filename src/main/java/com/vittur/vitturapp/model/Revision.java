package com.vittur.vitturapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revision {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    private Integer idRevision;
    @Column(name = "review_date")
    private LocalDate fechaRevision;
    @Column(name = "actual_km")
    private Integer kilometrajeActual;
    @Column(name = "review_note")
    private String diagnosticoResultado;
    @Column(name = "import")
    private float importe;

    @ManyToOne
    @JoinColumn(name = "plate")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
}
