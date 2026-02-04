package com.sqt.ad_proyectospring_alquilerpeliculas.repository;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Categoria;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Idioma;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByTipoProducto (Producto.Tipo tipo);
    boolean existsByAnioPublicacion(int anioPublicacion);
    List<Producto> findByCategoriasIn(List<Categoria> categorias);
    List<Producto> findByIdiomasContaining(Idioma idioma);

}
