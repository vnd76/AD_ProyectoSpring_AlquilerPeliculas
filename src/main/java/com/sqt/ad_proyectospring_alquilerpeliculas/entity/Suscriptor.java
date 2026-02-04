package com.sqt.ad_proyectospring_alquilerpeliculas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suscriptores")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Suscriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String correoElectronico;

    @Column(nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanContratado planContratado;

    @OneToMany(mappedBy = "suscriptor", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Alquiler> alquileres = new ArrayList<>();

    public enum PlanContratado {
        BASICO,
        PREMIUM
    }
}
