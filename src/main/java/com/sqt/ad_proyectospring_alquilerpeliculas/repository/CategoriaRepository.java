package com.sqt.ad_proyectospring_alquilerpeliculas.repository;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);
}
