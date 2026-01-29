package service;

import dto.request.IdiomaDTO;
import dto.response.IdiomaDetalleDTO;
import entity.Idioma;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.IdiomaMapper;
import org.springframework.stereotype.Service;
import repository.IdiomaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class IdiomaService {
    private final IdiomaRepository idiomaRepository;
    private final IdiomaMapper idiomaMapper;

    // ========== LISTAR TODOS ==========
    public List<IdiomaDTO> listarTodos(){
        log.info("Listando todos os idiomas");

        List<Idioma> idiomas = idiomaRepository.findAll();
        return idiomaMapper.toDTOList(idiomas);
    }

    // ========== BUSCAR POR ID ==========
    public IdiomaDetalleDTO buscarPorId(Long id){
        log.info("Buscando idioma por id");

        Idioma idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Idioma no encontrado con id: " + id
                ));
        return idiomaMapper.toDetalleDTO(idioma);
    }
    // ========== CREAR ==========
    @Transactional
    public IdiomaDetalleDTO crear(IdiomaDTO dto){
        log.info("Creando idioma por id");
        if(idiomaRepository.existsByNombre(dto.getNombre())){
            throw new IllegalArgumentException(
                    "Ya existe un idioma con el nombre: " + dto.getNombre()
            );
        }
        Idioma idioma = idiomaMapper.toEntity(dto);
        Idioma guardado = idiomaRepository.save(idioma);
        return idiomaMapper.toDetalleDTO(guardado);
    }
    // ========== BORRAR ==========
    @Transactional
    public void eliminar(Long id){
        log.info("Eliminando idioma por id");

        if(!idiomaRepository.existsById(id)){
            throw new RuntimeException(
                    "Idioma no encontrado con id: " + id
            );
        }
        idiomaRepository.deleteById(id);
    }

}
