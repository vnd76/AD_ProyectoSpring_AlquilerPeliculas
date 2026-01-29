package repository;

import entity.Alquiler;
import entity.Suscriptor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findBySuscriptor(Suscriptor suscriptor);

}
