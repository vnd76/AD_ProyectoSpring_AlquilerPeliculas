package dto.request;

import entity.Producto;
import jakarta.validation.constraints.*;
import lombok.*;

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
    @Size(max=4)
    private int anioPublicacion;
    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;


}
