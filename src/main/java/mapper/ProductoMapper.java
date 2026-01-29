package mapper;

import dto.request.ProductoDTO;
import dto.response.ProductoDetalleDTO;
import entity.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring")
public interface ProductoMapper {
    // =========== PARA DEVOLVER LISTA ===============
    List<ProductoDTO> toDTOList(List<Producto> productos);

    // =========== PARA DETALLES ===============
    @Mapping(source="anioPublicacion", target="anioPublicacion", dateFormat ="dd/MM/yyyy")
    ProductoDetalleDTO toDetalleDTO(Producto producto);
    // =========== PARA CREAR ===============
    @Mapping(target = "id", ignore = true)
    Producto toEntity(ProductoDTO productoDTO);
    // =========== PARA ACTUALIZAR ===============
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(ProductoDTO productoDTO, @MappingTarget Producto producto);
}
