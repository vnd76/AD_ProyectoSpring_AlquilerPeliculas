package com.sqt.ad_proyectospring_alquilerpeliculas.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlquilerDTO {
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @NotBlank(message = "La identificación del suscriptor es obligatoria")
    private Long suscriptorId;

}
