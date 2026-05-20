package com.vittur.vitturapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anyo_fabricacion")
    private String anyoFabricacion;
    @Column(name = "tipo_vehiculo")
    private String tipoVehiculo;

    @OneToMany(mappedBy = "vehiculo")
    private List<Revision> revisiones;

    @ManyToMany(mappedBy = "vehiculos")
    private List<Usuario> usuarios;
}
