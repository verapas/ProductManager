package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.resources.category.dto.CategoryCreateDto;
import ch.csbe.productmanager.resources.category.dto.CategoryDetailDto;
import ch.csbe.productmanager.resources.category.dto.CategoryShowDto;
import ch.csbe.productmanager.resources.category.dto.CategoryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Der CategoryMapper wandelt die Category-Entitäten in
 * DTO's um und umgekehrt.
 */
@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    public abstract CategoryShowDto toShowDto(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    public abstract CategoryDetailDto toDetailDto(Category category);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    public abstract Category toEntity(CategoryCreateDto categoryCreateDto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    public abstract void updateEntity(CategoryUpdateDto categoryUpdateDto, @MappingTarget Category category);
}
