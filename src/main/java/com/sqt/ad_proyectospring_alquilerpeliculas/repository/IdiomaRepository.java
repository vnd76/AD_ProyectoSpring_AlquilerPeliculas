package com.sqt.ad_proyectospring_alquilerpeliculas.repository;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
    boolean existsByNombre(String nombre);
}
