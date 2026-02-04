package com.sqt.ad_proyectospring_alquilerpeliculas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alquileres")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFin;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suscriptor_id", nullable = false)
    private Suscriptor suscriptor;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    /**
     * Calcula la fecha fin del alquiler según el plan del suscriptor
     * BASICO: 2 semanas
     * PREMIUM: 4 semanas
     */
    public void calcularFechaFin() {
        if (this.suscriptor != null && this.fechaInicio != null) {
            int diasAlquiler = this.suscriptor.getPlanContratado() == Suscriptor.PlanContratado.PREMIUM ? 28 : 14;
            this.fechaFin = this.fechaInicio.plusDays(diasAlquiler);
        }
    }

}
