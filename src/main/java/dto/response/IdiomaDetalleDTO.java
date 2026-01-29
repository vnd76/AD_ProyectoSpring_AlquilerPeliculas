package dto.response;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdiomaDetalleDTO {
    private Long id;
    private String nombre;
}
