package com.sqt.ad_proyectospring_alquilerpeliculas.mapper;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.request.AlquilerDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.dto.response.AlquilerDetalleDTO;
import com.sqt.ad_proyectospring_alquilerpeliculas.entity.Alquiler;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring")
public interface AlquilerMapper {

    // =========== PARA DEVOLVER LISTA ===============
    List<AlquilerDetalleDTO> toDetalleDTOList(List<Alquiler> alquileres);

    // =========== PARA DETALLES ===============
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "suscriptor.id", target = "suscriptorId")
    AlquilerDetalleDTO toDetalleDTO(Alquiler alquiler);

    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "suscriptor", ignore = true)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "fechaFin", ignore = true) // Se calcula automáticamente
    Alquiler toEntity(AlquilerDTO dto);

    // ========== PARA ACTUALIZAR ==========
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "suscriptor", ignore = true)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "fechaFin", ignore = true) // Se recalcula automáticamente
    void updateEntityFromDTO(AlquilerDTO dto, @MappingTarget Alquiler entity);
}