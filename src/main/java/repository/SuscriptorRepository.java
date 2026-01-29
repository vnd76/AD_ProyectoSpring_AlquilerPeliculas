package repository;

import entity.Suscriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscriptorRepository extends JpaRepository<Suscriptor, Long> {
    boolean existsByCorreoElectronico(String correo);
    boolean existsByPlan (Suscriptor.PlanContratado planContratado);
}
