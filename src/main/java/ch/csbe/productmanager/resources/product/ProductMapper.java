package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.resources.product.dto.ProductCreateDto;
import ch.csbe.productmanager.resources.product.dto.ProductShowDto;
import ch.csbe.productmanager.resources.product.dto.ProductUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper-Klasse für die Umwandlung von Produkt-Entitäten in DTOs und umgekehrt.
 * Verwendet MapStruct zur automatischen Mappung von Feldern.
 */
@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    public abstract ProductShowDto toShowDto(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    public abstract Product toEntity(ProductCreateDto dto);

    @Mapping(source = "categoryId", target = "category.id")
    public abstract void updateEntity(ProductUpdateDto dto, @MappingTarget Product product);
}
