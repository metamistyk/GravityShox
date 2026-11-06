package cl.gravityshox.catalogo.dto;

import java.time.LocalDateTime;

public record MarcaResponse(
    Long id,
    String nombre,
    LocalDateTime createdAt
) {

}
