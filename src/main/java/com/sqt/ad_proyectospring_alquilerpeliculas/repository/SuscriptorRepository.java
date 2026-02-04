package com.sqt.ad_proyectospring_alquilerpeliculas.repository;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscriptorRepository extends JpaRepository<Suscriptor, Long> {
    boolean existsByCorreoElectronico(String correo);
    boolean existsByPlanContratado(Suscriptor.PlanContratado planContratado);

}
