package cl.gravityshox.catalogo.dto;

import java.time.LocalDateTime;

public record CategoriaResponse(
    Long id,
    String nombre,
    LocalDateTime createdAt
) {

}
