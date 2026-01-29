package service;


import dto.request.AlquilerDTO;
import dto.response.AlquilerDetalleDTO;
import entity.Alquiler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.AlquilerMapper;
import org.springframework.stereotype.Service;
import repository.AlquilerRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final AlquilerMapper alquilerMapper;

    // ========== LISTAR TODOS ==========
    public List<AlquilerDetalleDTO> listarTodos(){
        log.info("Iniciando lista de alquileres");
        List<Alquiler> alquileres = alquilerRepository.findAll();

        return alquilerMapper.toDetalleDTOList(alquileres);
    }

    // ========== BUSCAR POR ID ==========
    public AlquilerDetalleDTO buscarPorId(Long id){
        log.info("Iniciando buscar alquiler por id");
        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Alquiler no encontrado con id: "+id
                ));
        return alquilerMapper.toDetalleDTO(alquiler);
    }
    // ========== CREAR ==========
    @Transactional
    public AlquilerDetalleDTO crear(AlquilerDTO dto){
        log.info("Iniciando crear alquiler");

        Alquiler alquiler = alquilerMapper.toEntity(dto);
        Alquiler guardado = alquilerRepository.save(alquiler);
        return alquilerMapper.toDetalleDTO(guardado);
    }
    // ========== ACTUALIZAR ==========
    @Transactional
    public AlquilerDetalleDTO actualizar(Long id, AlquilerDTO dto){
        log.info("Iniciando actualizar alquiler por id");

        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Alquiler no encontrado con id: "+id
                ));

        alquilerMapper.updateEntityFromDTO(dto, alquiler);
        Alquiler actualizado = alquilerRepository.save(alquiler);
        return alquilerMapper.toDetalleDTO(actualizado);
    }
    // ========== ELIMINAR ==========
    @Transactional
    public void eliminar(Long id){
        log.info("Iniciando eliminar alquiler por id");

        if(!alquilerRepository.existsById(id)){
            throw new RuntimeException("Alquiler no encontrado con id: "+id);
        }

        alquilerRepository.deleteById(id);
    }

}
