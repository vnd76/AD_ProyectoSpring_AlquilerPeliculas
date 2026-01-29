package dto.response;

import entity.Suscriptor;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuscriptorDetalleDTO {
    private Long id;
    private String correoElectronico;
    private String contrasenia;
    private Suscriptor.PlanContratado planContratado;
}
