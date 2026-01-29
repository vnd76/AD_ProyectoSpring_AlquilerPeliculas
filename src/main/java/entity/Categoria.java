package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table (name = "categorias")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<>();
}
