package cl.gravityshox.catalogo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import cl.gravityshox.catalogo.dto.CategoriaRequest;
import cl.gravityshox.catalogo.dto.CategoriaResponse;
import cl.gravityshox.catalogo.entity.Categoria;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoriaMapper {

    CategoriaResponse toResponse(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Categoria toEntity(CategoriaRequest categoriaRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(CategoriaRequest CategoriRequest, @MappingTarget Categoria categoria);

}