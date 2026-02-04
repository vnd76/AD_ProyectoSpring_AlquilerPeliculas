package com.sqt.ad_proyectospring_alquilerpeliculas.mapper;

import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Suscriptor;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.SuscriptorDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.SuscriptorDetalleDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring")
public interface SuscriptorMapper {
    // =========== PARA DEVOLVER LISTA ===============
    List<SuscriptorDTO> toDTOList(List<Suscriptor> suscriptores);
    // =========== PARA DETALLES ===============
    SuscriptorDetalleDTO toDetalleDTO(Suscriptor suscriptor);
    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    Suscriptor toEntity(SuscriptorDTO suscriptorDTO);
    // =========== PARA ACTUALIZAR ===============
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(SuscriptorDTO  suscriptorDTO, @MappingTarget Suscriptor suscriptor);
}
