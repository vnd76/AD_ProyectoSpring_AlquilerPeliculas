package com.sqt.ad_proyectospring_alquilerpeliculas.repository;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Alquiler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findBySuscriptor(Suscriptor suscriptor);

    @Query("SELECT a FROM Alquiler a WHERE a.suscriptor.planContratado = :plan " +
            "AND a.fechaFin >= CURRENT_DATE")
    Page<Alquiler> findAlquileresActivosPorPlan(
            @Param("plan") Suscriptor.PlanContratado plan,
            Pageable pageable
    );
}
