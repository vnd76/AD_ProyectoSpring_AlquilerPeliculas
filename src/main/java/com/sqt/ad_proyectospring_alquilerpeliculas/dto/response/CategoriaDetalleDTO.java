package com.sqt.ad_proyectospring_alquilerpeliculas.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CategoriaDetalleDTO {
    private Long id;
    private String nombre;
}
