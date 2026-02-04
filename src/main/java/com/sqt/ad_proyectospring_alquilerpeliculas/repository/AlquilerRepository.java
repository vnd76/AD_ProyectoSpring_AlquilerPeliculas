package com.sqt.ad_proyectospring_alquilerpeliculas.repository;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findBySuscriptor(Suscriptor suscriptor);

}
