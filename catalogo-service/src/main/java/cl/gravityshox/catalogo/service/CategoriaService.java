package cl.gravityshox.catalogo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.gravityshox.catalogo.dto.CategoriaRequest;
import cl.gravityshox.catalogo.dto.CategoriaResponse;
import cl.gravityshox.catalogo.entity.Categoria;
import cl.gravityshox.catalogo.exception.RecursoDuplicadoException;
import cl.gravityshox.catalogo.exception.RecursoNoEncontradoException;
import cl.gravityshox.catalogo.mapper.CategoriaMapper;
import cl.gravityshox.catalogo.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponse> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponse obtenerPorId(Long id) {
        return categoriaMapper.toResponse(buscarCategoria(id));
    }

    @Transactional
    public CategoriaResponse crear(CategoriaRequest request) {
        validarNombreDisponible(request.nombre());
        Categoria categoria = categoriaMapper.toEntity(request);
        Categoria guardada = categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(guardada);
    }

    @Transactional
    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {
        Categoria categoria = buscarCategoria(id);
        if (!categoria.getNombre().equalsIgnoreCase(request.nombre())) {
            validarNombreDisponible(request.nombre());
        }
        categoriaMapper.updateEntity(request, categoria);
        return categoriaMapper.toResponse(categoria);
    }

    @Transactional
    public void eliminar(Long id) {
        Categoria categoria = buscarCategoria(id);
        categoriaRepository.delete(categoria);
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una categoria con el id " + id));
    }

    private void validarNombreDisponible(String nombre) {
        if (categoriaRepository.existsByNombreIgnoreCase(nombre)) {
            throw new RecursoDuplicadoException(
                    "Ya existe categoria con el nombre " + nombre);
        }
    }
}
