package com.sqt.ad_proyectospring_alquilerpeliculas.dto.request;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Producto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;

    @NotNull(message = "Elige un tipo de producto")
    private Producto.Tipo tipoProducto;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotNull(message = "El año de publicación es obligatorio")
    @Min(value = 1895, message = "El año debe ser 1895 o posterior")
    @Max(value = 2026, message = "El año no puede ser mayor a 2026")
    private int anioPublicacion;
    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El producto debe pertenecer al menos a una categoría")
   private Set<Long> categoriasId;

    @NotNull(message = "El producto debe tener al menos un idioma")
    private Set<Long> idiomasId;

}
