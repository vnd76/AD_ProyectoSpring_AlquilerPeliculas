package com.sqt.ad_proyectospring_alquilerpeliculas.dto.response;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Producto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDetalleDTO {
    private Long id;
    private Producto.Tipo tipo;
    private String titulo;
    private int anioPublicacion;
    private String descripcion;

}
