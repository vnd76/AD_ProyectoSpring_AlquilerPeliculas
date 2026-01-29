package service;

import dto.request.ProductoDTO;
import dto.response.ProductoDetalleDTO;
import entity.Producto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.ProductoMapper;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    // ========== LISTAR TODOS ==========
    public List<ProductoDTO> listarTodos() {
        log.info("Listando todos los productos");
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toDTOList(productos);
    }
    // ========== BUSCAR POR ID ==========
public ProductoDetalleDTO buscarPorId(Long id){
        log.info("Buscando productos por id");
        Producto  producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(
                        "Producto no encontrada con id: " + id
                ));
        return productoMapper.toDetalleDTO(producto);
}
    // ========== CREAR ==========
    @Transactional
    public ProductoDetalleDTO crear(ProductoDTO dto){
        log.info("Creando producto");
        if(productoRepository.existsByTitulo(dto.getTitulo())){
            throw new IllegalArgumentException(
                    "Producto ya existe con título: " + dto.getTitulo()
            );
        }

        Producto producto = productoMapper.toEntity(dto);
        productoRepository.save(producto);
        return productoMapper.toDetalleDTO(producto);
    }

    // ========== ACTUALIZAR ==========
    @Transactional
    public ProductoDetalleDTO actualizar(ProductoDTO dto){
        log.info("Actualizando producto");

        Producto producto = productoRepository.findById(dto.getId())
                .orElseThrow(()-> new RuntimeException(
                        "Producto no encontrada con id: " + dto.getId()
                ));

        if(!producto.getTitulo().equals(dto.getTitulo())
        && productoRepository.existsByTitulo(dto.getTitulo())) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con titulo: " + dto.getTitulo()
            );
        }

            productoMapper.updateEntityFromDTO(dto, producto);
            Producto actualizado = productoRepository.save(producto);
            return productoMapper.toDetalleDTO(actualizado);
        }


    // ========== ELIMINAR ==========
    @Transactional
    public void eliminar(Long id){
        log.info("Eliminando producto");
        if(!productoRepository.existsById(id)){
            throw new RuntimeException(
                    "Producto no encontrada con id: " + id
            );
        }
        productoRepository.deleteById(id);
    }
}
