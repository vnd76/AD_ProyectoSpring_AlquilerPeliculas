package repository;

import entity.Categoria;
import entity.Idioma;
import entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByTipoProducto (Producto.Tipo tipo);
    boolean existsByAnioPublicacion(int anioPublicacion);
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByIdioma(Idioma idioma);
}
