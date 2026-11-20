package cl.gravityshox.catalogo.mapper;

import cl.gravityshox.catalogo.dto.MarcaRequest;
import cl.gravityshox.catalogo.dto.MarcaResponse;
import cl.gravityshox.catalogo.entity.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MarcaMapper {

    MarcaResponse toResponse(Marca marca);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Marca toEntity(MarcaRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(MarcaRequest request, @MappingTarget Marca marca);
}