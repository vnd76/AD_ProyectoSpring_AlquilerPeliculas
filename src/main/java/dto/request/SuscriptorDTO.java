package dto.request;

import entity.Suscriptor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuscriptorDTO {
    private Long id;

    @NotNull(message = "El correo electrónico es obligatorio")
    private String correoElectronico;
    @NotNull(message = "La contraseña es obligatoria")
    private String contrasenia;
    @NotNull(message = "Elige un plan")
    private Suscriptor.PlanContratado planContratado;


}
