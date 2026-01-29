package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alquileres")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suscriptor_id", nullable = false)
    private Suscriptor suscriptor;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();



}
