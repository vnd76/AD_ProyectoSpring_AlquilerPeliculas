package com.sqt.ad_proyectospring_alquilerpeliculas.service;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.ProductoDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.ProductoDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Categoria;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Idioma;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Producto;
import com.sqt.ad_proyectospring_alquilerpeliculas.mapper.ProductoMapper;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.CategoriaRepository;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.IdiomaRepository;
import com.sqt.ad_proyectospring_alquilerpeliculas.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;
    private final IdiomaRepository idiomaRepository;

    // ========== LISTAR TODOS ==========
    public List<ProductoDTO> listarTodos() {
        log.info("Listando todos los productos");
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toDTOList(productos);
    }

    // ========== BUSCAR POR ID ==========
    public ProductoDetalleDTO buscarPorId(Long id){
        log.info("Buscando productos por id: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(
                        "Producto no encontrado con id: " + id
                ));
        return productoMapper.toDetalleDTO(producto);
    }

    // ========== CREAR ==========
    @Transactional
    public ProductoDetalleDTO crear(ProductoDTO dto){
        log.info("Creando producto");

        // Validaciones
        if(productoRepository.existsByTitulo(dto.getTitulo())){
            throw new IllegalArgumentException(
                    "Producto ya existe con título: " + dto.getTitulo()
            );
        }

        if (dto.getCategoriasId() == null || dto.getCategoriasId().isEmpty()) {
            throw new IllegalArgumentException(
                    "El producto debe pertenecer al menos a una categoría"
            );
        }

        if (dto.getIdiomasId() == null || dto.getIdiomasId().isEmpty()) {
            throw new IllegalArgumentException(
                    "El producto debe tener al menos un idioma"
            );
        }

        Producto producto = productoMapper.toEntity(dto);

        // Asignar categorías al producto
        Set<Categoria> categorias = new HashSet<>();
        for (Long categoriaId : dto.getCategoriasId()) {
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException(
                            "Categoría no encontrada con id: " + categoriaId
                    ));
            categorias.add(categoria);
        }
        producto.setCategorias(categorias);

        // Asignar idiomas al producto
        Set<Idioma> idiomas = new HashSet<>();
        for (Long idiomaId : dto.getIdiomasId()) {
            Idioma idioma = idiomaRepository.findById(idiomaId)
                    .orElseThrow(() -> new RuntimeException(
                            "Idioma no encontrado con id: " + idiomaId
                    ));
            idiomas.add(idioma);
        }
        producto.setIdiomas(idiomas);

        Producto guardado = productoRepository.save(producto);
        log.info("Producto creado con {} categorías y {} idiomas",
                categorias.size(), idiomas.size());

        return productoMapper.toDetalleDTO(guardado);
    }

    // ========== ACTUALIZAR ==========
    @Transactional
    public ProductoDetalleDTO actualizar(Long id, ProductoDTO dto){
        log.info("Actualizando producto con id: {}", id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(
                        "Producto no encontrado con id: " + id
                ));

        // Validaciones
        if(!producto.getTitulo().equals(dto.getTitulo())
                && productoRepository.existsByTitulo(dto.getTitulo())) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con título: " + dto.getTitulo()
            );
        }

        if (dto.getCategoriasId() == null || dto.getCategoriasId().isEmpty()) {
            throw new IllegalArgumentException(
                    "El producto debe pertenecer al menos a una categoría"
            );
        }

        if (dto.getIdiomasId() == null || dto.getIdiomasId().isEmpty()) {
            throw new IllegalArgumentException(
                    "El producto debe tener al menos un idioma"
            );
        }

        // Actualizar datos básicos del producto
        productoMapper.updateEntityFromDTO(dto, producto);

        // Actualizar categorías
        Set<Categoria> categorias = new HashSet<>();
        for (Long categoriaId : dto.getCategoriasId()) {
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException(
                            "Categoría no encontrada con id: " + categoriaId
                    ));
            categorias.add(categoria);
        }
        producto.setCategorias(categorias);

        // Actualizar idiomas
        Set<Idioma> idiomas = new HashSet<>();
        for (Long idiomaId : dto.getIdiomasId()) {
            Idioma idioma = idiomaRepository.findById(idiomaId)
                    .orElseThrow(() -> new RuntimeException(
                            "Idioma no encontrado con id: " + idiomaId
                    ));
            idiomas.add(idioma);
        }
        producto.setIdiomas(idiomas);

        Producto actualizado = productoRepository.save(producto);
        log.info("Producto actualizado con {} categorías y {} idiomas",
                categorias.size(), idiomas.size());

        return productoMapper.toDetalleDTO(actualizado);
    }

    // ========== ELIMINAR ==========
    @Transactional
    public void eliminar(Long id){
        log.info("Eliminando producto con id: {}", id);

        if(!productoRepository.existsById(id)){
            throw new RuntimeException(
                    "Producto no encontrado con id: " + id
            );
        }

        productoRepository.deleteById(id);
    }
}


