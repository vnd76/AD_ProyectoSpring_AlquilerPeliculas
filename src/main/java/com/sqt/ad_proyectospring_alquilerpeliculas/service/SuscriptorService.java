package com.sqt.ad_proyectospring_alquilerpeliculas.service;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.SuscriptorDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.SuscriptorDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import com.sqt.ad_proyectospring_alquilerpeliculas.mapper.SuscriptorMapper;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.SuscriptorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SuscriptorService {

    private final SuscriptorRepository suscriptorRepository;
    private final SuscriptorMapper suscriptorMapper;

    // ========== LISTAR TODOS ==========
    public List<SuscriptorDTO> listarTodos() {
        log.info("Listando todos los suscriptores");
        List<Suscriptor> suscriptores = suscriptorRepository.findAll();
        return suscriptorMapper.toDTOList(suscriptores);
    }

    // ========== BUSCAR POR ID ==========
    public SuscriptorDetalleDTO buscarPorId(Long id) {
        log.info("Buscando suscriptor por id");
        Suscriptor suscriptor = suscriptorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Suscriptor no encontrado con id: " + id
                ));
        return suscriptorMapper.toDetalleDTO(suscriptor);
    }

    // ========== CREAR ==========
    @Transactional
    public SuscriptorDetalleDTO crear(SuscriptorDTO dto) {
        log.info("Creando suscriptor");

        if (suscriptorRepository.existsByCorreoElectronico(dto.getCorreoElectronico())) {
            throw new IllegalArgumentException(
                    "Ya existe un suscriptor con el correo: " + dto.getCorreoElectronico()
            );
        }

        Suscriptor suscriptor = suscriptorMapper.toEntity(dto);
        Suscriptor guardado = suscriptorRepository.save(suscriptor);
        return suscriptorMapper.toDetalleDTO(guardado);
    }

    // ========== ACTUALIZAR ==========
    @Transactional
    public SuscriptorDetalleDTO actualizar(Long id, SuscriptorDTO dto) {
        log.info("Actualizando suscriptor por id");

        Suscriptor suscriptor = suscriptorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Suscriptor no encontrado con id: " + id
                ));

        if (!suscriptor.getCorreoElectronico().equals(dto.getCorreoElectronico())
                && suscriptorRepository.existsByCorreoElectronico(dto.getCorreoElectronico())) {
            throw new IllegalArgumentException(
                    "Ya existe un suscriptor con el correo: " + dto.getCorreoElectronico()
            );
        }

        suscriptorMapper.updateEntityFromDTO(dto, suscriptor);
        Suscriptor actualizado = suscriptorRepository.save(suscriptor);
        return suscriptorMapper.toDetalleDTO(actualizado);
    }

    // ========== ELIMINAR ==========
    @Transactional
    public void eliminar(Long id) {
        log.info("Eliminando suscriptor por id");

        if (!suscriptorRepository.existsById(id)) {
            throw new RuntimeException(
                    "Suscriptor no encontrado con id: " + id
            );
        }

        suscriptorRepository.deleteById(id);
    }
}