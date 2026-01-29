package mapper;
import dto.request.AlquilerDTO;
import dto.response.AlquilerDetalleDTO;
import entity.Alquiler;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring")
public interface AlquilerMapper {

    // =========== PARA DEVOLVER LISTA ===============
    List<AlquilerDetalleDTO> toDetalleDTOList(List<Alquiler> alquileres);

    // =========== PARA DETALLES ===============
    @Mapping(source="fechaInicio", target="fechaInicio", dateFormat ="dd/MM/yyyy")
    @Mapping(source="fechaFin", target="fechaFin", dateFormat ="dd/MM/yyyy")
    AlquilerDetalleDTO toDetalleDTO(Alquiler alquiler);

    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    Alquiler toEntity(AlquilerDTO dto);

    // ========== PARA ACTUALIZAR ==========
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(AlquilerDTO dto, @MappingTarget Alquiler entity);
}
