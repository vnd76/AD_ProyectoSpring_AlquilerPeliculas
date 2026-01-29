package repository;

import entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
    boolean existsByNombre(String nombre);
}
