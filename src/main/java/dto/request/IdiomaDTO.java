package dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdiomaDTO {
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    private String nombre;
}
