package com.sqt.ad_proyectospring_alquilerpeliculas.mapper;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.CategoriaDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.CategoriaDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Categoria;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring")
public interface CategoriaMapper {
    // =========== PARA DEVOLVER LISTA ==============
    List<CategoriaDTO> toDTOList(List<Categoria> categorias);
    // =========== PARA DETALLES ===============
    CategoriaDetalleDTO toDetalleDTO(Categoria categoria);
    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CategoriaDTO dto);
    // =========== PARA ACTUALIZAR ===============
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(CategoriaDTO dto, @MappingTarget Categoria categoria);
}
