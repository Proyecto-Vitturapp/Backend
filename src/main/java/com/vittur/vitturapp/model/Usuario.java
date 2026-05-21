package com.vittur.vitturapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "email")
    private String email;
    @Column(name = "rol")
    private String rol;
    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Revision> revisiones;

    @ManyToMany
    @JoinTable(
            name = "usuarios_vehiculos",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "matricula")
    )
    private List<Vehiculo> vehiculos;

    @PrePersist
    private void setFechaAlta(){
        this.fechaAlta = LocalDate.now();
    }
}
