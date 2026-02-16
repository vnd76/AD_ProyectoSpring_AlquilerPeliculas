package com.sqt.ad_proyectospring_alquilerpeliculas.service;


import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.SuscriptorRepository;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.AlquilerDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.AlquilerDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Alquiler;
import com.sqt.ad_proyectospring_alquilerpeliculas.mapper.AlquilerMapper;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.AlquilerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional


public class AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final AlquilerMapper alquilerMapper;
    private final SuscriptorRepository suscriptorRepository;

    // ========== BUSCAR ALQUILERES ACTIVOS POR PLAN ==========
    public Page<AlquilerDetalleDTO> buscarAlquileresActivosPorPlan(
            Suscriptor.PlanContratado plan,
            Pageable pageable) {

        log.info("Buscando alquileres activos para el plan: {}", plan);

        Page<Alquiler> alquileres = alquilerRepository.findAlquileresActivosPorPlan(plan, pageable);

        return alquileres.map(alquilerMapper::toDetalleDTO);
    }

    // ========== LISTAR TODOS ==========
    public List<AlquilerDetalleDTO> listarTodos(){
        log.info("Iniciando lista de alquileres");
        List<Alquiler> alquileres = alquilerRepository.findAll();
        return alquilerMapper.toDetalleDTOList(alquileres);
    }

    // ========== BUSCAR POR ID ==========
    public AlquilerDetalleDTO buscarPorId(Long id){
        log.info("Iniciando buscar alquiler por id: {}", id);
        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Alquiler no encontrado con id: " + id
                ));
        return alquilerMapper.toDetalleDTO(alquiler);
    }

    // ========== CREAR ==========
    @Transactional
    public AlquilerDetalleDTO crear(AlquilerDTO dto){
        log.info("Iniciando crear alquiler");

        // Validar que la fecha de inicio sea hoy o futura
        if (dto.getFechaInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de inicio debe ser la fecha actual o una fecha futura"
            );
        }

        Suscriptor suscriptor = suscriptorRepository.findById(dto.getSuscriptorId())
                .orElseThrow(() -> new RuntimeException(
                        "Suscriptor no encontrado con id: " + dto.getSuscriptorId()
                ));

        Alquiler alquiler = alquilerMapper.toEntity(dto);
        alquiler.setSuscriptor(suscriptor);

        // Calcular fecha de fin según el plan del suscriptor automáticamente
        alquiler.calcularFechaFin();

        Alquiler guardado = alquilerRepository.save(alquiler);
        log.info("Alquiler creado con fecha inicio: {} y fecha fin: {}",
                guardado.getFechaInicio(), guardado.getFechaFin());

        return alquilerMapper.toDetalleDTO(guardado);
    }

    // ========== ACTUALIZAR ==========
    @Transactional
    public AlquilerDetalleDTO actualizar(Long id, AlquilerDTO dto){
        log.info("Iniciando actualizar alquiler por id: {}", id);

        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Alquiler no encontrado con id: " + id
                ));

        // Validaciones
        if (dto.getFechaInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La fecha de inicio debe ser la fecha actual o una fecha futura"
            );
        }

        if (dto.getSuscriptorId() != null &&
                !dto.getSuscriptorId().equals(alquiler.getSuscriptor().getId())) {
            Suscriptor nuevoSuscriptor = suscriptorRepository.findById(dto.getSuscriptorId())
                    .orElseThrow(() -> new RuntimeException(
                            "Suscriptor no encontrado con id: " + dto.getSuscriptorId()
                    ));
            alquiler.setSuscriptor(nuevoSuscriptor);
        }

        alquilerMapper.updateEntityFromDTO(dto, alquiler);

        // Al actualizarse, se vuelve a calcular la fecha de fin de alquiler dependiendo del plan
        alquiler.calcularFechaFin();

        Alquiler actualizado = alquilerRepository.save(alquiler);
        log.info("Alquiler actualizado con fecha inicio:" +actualizado.getFechaInicio()+  "y fecha fin: " +actualizado.getFechaFin());

        return alquilerMapper.toDetalleDTO(actualizado);
    }

    // ========== ELIMINAR ==========
    @Transactional
    public void eliminar(Long id){
        log.info("Iniciando eliminar alquiler por id");

        if(!alquilerRepository.existsById(id)){
            throw new RuntimeException("Alquiler no encontrado con id: " + id);
        }

        alquilerRepository.deleteById(id);
    }
}
