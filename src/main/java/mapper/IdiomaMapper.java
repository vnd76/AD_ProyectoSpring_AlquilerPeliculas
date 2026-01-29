package mapper;

import dto.request.IdiomaDTO;
import dto.response.IdiomaDetalleDTO;
import entity.Idioma;
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
