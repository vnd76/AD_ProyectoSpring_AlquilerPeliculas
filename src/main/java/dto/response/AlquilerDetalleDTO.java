package dto.response;

import entity.Alquiler;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerDetalleDTO extends Alquiler {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long suscriptorId;
}