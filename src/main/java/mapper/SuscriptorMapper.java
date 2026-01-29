package mapper;

import dto.request.SuscriptorDTO;
import dto.response.SuscriptorDetalleDTO;
import entity.Producto;
import entity.Suscriptor;
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
