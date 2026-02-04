package com.sqt.ad_proyectospring_alquilerpeliculas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "productos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipoProducto;

    @Column (nullable=false)
    private String titulo;

    @Column
    private String descripcion;
    @Column(length=4)
    private int anioPublicacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_idioma",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private Set<Idioma> idiomas = new HashSet<>();

    @ManyToOne(optional = true)
    @JoinColumn(name = "alquiler_id", nullable = true)
    private Alquiler alquiler;


    public enum Tipo {
        PELICULA,
        SERIE
    }

}
