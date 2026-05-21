package com.vittur.vitturapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @Column(name = "plate")
    private String matricula;
    @Column(name = "brand")
    private String marca;
    @Column(name = "model")
    private String modelo;
    @Column(name = "fabrication_year")
    private String anyoFabricacion;
    @Column(name = "vehicle_type")
    private String tipoVehiculo;

    @OneToMany(mappedBy = "vehiculo")
    private List<Revision> revisiones;

    @ManyToMany(mappedBy = "vehiculos")
    private List<Usuario> usuarios;
}
