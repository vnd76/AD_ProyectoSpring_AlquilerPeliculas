package service;

import dto.request.CategoriaDTO;
import dto.response.CategoriaDetalleDTO;
import entity.Categoria;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.CategoriaMapper;
import org.springframework.stereotype.Service;
import repository.CategoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper  categoriaMapper;

    // ========== LISTAR TODOS ==========
    public List<CategoriaDTO> listarTodos(){
        log.info("Iniciando lista de categorias");

        List<Categoria> categorias = categoriaRepository.findAll();
        return categoriaMapper.toDTOList(categorias);
    }
    // ========== BUSCAR POR ID ==========
    public CategoriaDetalleDTO buscarPorId(Long id){
        log.info("Iniciando busca de categoria");

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Categoria no encontrada con id: " +id
                ));
        return categoriaMapper.toDetalleDTO(categoria);
    }

    // ========== CREAR ==========
    @Transactional
    public CategoriaDetalleDTO crear(CategoriaDTO dto){
        log.info("Iniciando crea de categoria");

        if(categoriaRepository.existsByNombre(dto.getNombre())){
            throw new IllegalArgumentException(
                    "Ya existe una categoria con el nombre: " + dto.getNombre()
            );
        }
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDetalleDTO(categoria);
    }
    // ========== ELIMINAR ==========
    @Transactional
    public void eliminar(Long id){
        log.info("Iniciando eliminar de categoria");
        if(!categoriaRepository.existsById(id)){
            throw new RuntimeException(
                    "Categoria no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
