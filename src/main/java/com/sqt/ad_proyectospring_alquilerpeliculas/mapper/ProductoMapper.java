package com.sqt.ad_proyectospring_alquilerpeliculas.mapper;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.ProductoDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.ProductoDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Categoria;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Idioma;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Producto;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
public interface ProductoMapper {

    // =========== PARA DEVOLVER LISTA ===============
    List<ProductoDTO> toDTOList(List<Producto> productos);

    // =========== CONVERSIÓN DE PRODUCTO A DTO ===============
    @Mapping(source = "categorias", target = "categoriasId")
    @Mapping(source = "idiomas", target = "idiomasId")
    ProductoDTO toDTO(Producto producto);

    // =========== PARA DETALLES ===============
    @Mapping(source = "tipoProducto", target = "tipo")
    ProductoDetalleDTO toDetalleDTO(Producto producto);

    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "idiomas", ignore = true)
    @Mapping(target = "alquiler", ignore = true)
    Producto toEntity(ProductoDTO productoDTO);

    // =========== PARA ACTUALIZAR ===============
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "idiomas", ignore = true)
    @Mapping(target = "alquiler", ignore = true)
    void updateEntityFromDTO(ProductoDTO productoDTO, @MappingTarget Producto producto);

    // =========== MÉTODOS AUXILIARES PARA CONVERSIÓN DE IDS ===============
    default Set<Long> mapCategoriasToIds(Set<Categoria> categorias) {
        if (categorias == null || categorias.isEmpty()) {
            return null;
        }
        return categorias.stream()
                .map(Categoria::getId)
                .collect(Collectors.toSet());
    }

    default Set<Long> mapIdiomasToIds(Set<Idioma> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return null;
        }
        return idiomas.stream()
                .map(Idioma::getId)
                .collect(Collectors.toSet());
    }
}