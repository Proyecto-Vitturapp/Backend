package com.vittur.vitturapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer idUsuario;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "creation_date")
    private LocalDate fechaAlta;
    @Column(name = "name")
    private String nombre;
    @Column(name = "first_last_name")
    private String apellido;
    @Column(name = "second_last_name")
    private String segundoApellido;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private Integer rol;
    @Column(name = "phone_number")
    private String telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Revision> revisiones;

    @ManyToMany
    @JoinTable(
            name = "users_vehicles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plate")
    )
    private List<Vehiculo> vehiculos;

    @PrePersist
    private void setFechaAltaAndRole(){
        this.fechaAlta = LocalDate.now();
        this.rol = 0;
    }
}
