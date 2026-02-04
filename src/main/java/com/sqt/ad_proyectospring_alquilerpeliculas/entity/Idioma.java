package com.sqt.ad_proyectospring_alquilerpeliculas.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="idiomas")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @ManyToMany(mappedBy = "idiomas")
    private Set<Producto> productos = new HashSet<>();
}
