package com.sqt.ad_proyectospring_alquilerpeliculas.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    private String nombre;
}
