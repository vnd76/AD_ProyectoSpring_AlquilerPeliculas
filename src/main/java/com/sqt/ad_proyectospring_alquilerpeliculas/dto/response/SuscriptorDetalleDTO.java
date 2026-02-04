package com.sqt.ad_proyectospring_alquilerpeliculas.dto.response;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
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
