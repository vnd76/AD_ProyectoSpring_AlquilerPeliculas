package com.sqt.ad_proyectospring_alquilerpeliculas.dto.response;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerDetalleDTO{
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long suscriptorId;
}