package com.sqt.ad_proyectospring_alquilerpeliculas.mapper;

import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.IdiomaDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.IdiomaDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Idioma;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring")
public interface IdiomaMapper {
    // =========== PARA DEVOLVER LISTA ===============
    List<IdiomaDTO> toDTOList(List<Idioma> idiomas);

    // =========== PARA DETALLES ===============
    IdiomaDetalleDTO toDetalleDTO(Idioma idioma);
    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    Idioma toEntity(IdiomaDTO dto);
    // =========== PARA ACTUALIZAR ===============
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(IdiomaDTO dto, @MappingTarget Idioma idioma);
}
