package cl.gravityshox.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
    @NotBlank(message = "No puede ser vacío.")
    @Size(max = 80, message = "La cantidad de caracteres no puede sobrepasar los 80")
    String nombre
) {

}
