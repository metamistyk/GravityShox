package cl.gravityshox.catalogo.service;

import cl.gravityshox.catalogo.dto.MarcaRequest;
import cl.gravityshox.catalogo.dto.MarcaResponse;
import cl.gravityshox.catalogo.entity.Marca;
import cl.gravityshox.catalogo.exception.RecursoDuplicadoException;
import cl.gravityshox.catalogo.exception.RecursoNoEncontradoException;
import cl.gravityshox.catalogo.mapper.MarcaMapper;
import cl.gravityshox.catalogo.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarcaService {
    
    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public List<MarcaResponse> listar() {
        return marcaRepository.findAll()
                .stream()
                .map(marcaMapper::toResponse)
                .toList();
    }

    public MarcaResponse obtenerPorId(Long id) {
        return marcaMapper.toResponse(buscarMarca(id));
    }

    @Transactional
    public MarcaResponse crear(MarcaRequest request) {
        validarNombreDisponible(request.nombre());
        Marca marca = marcaMapper.toEntity(request);
        Marca guardada = marcaRepository.save(marca);
        return marcaMapper.toResponse(guardada);
    }

    
    @Transactional
    public MarcaResponse actualizar(Long id, MarcaRequest request) {
        Marca marca = buscarMarca(id);
        if (!marca.getNombre().equalsIgnoreCase(request.nombre())) {
            validarNombreDisponible(request.nombre());
        }
        marcaMapper.updateEntity(request, marca);
        return marcaMapper.toResponse(marca);
    }

    @Transactional
    public void eliminar(Long id) {
        Marca marca = buscarMarca(id);
        marcaRepository.delete(marca);
    }

    private Marca buscarMarca(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la marca con id " + id));
    }

    private void validarNombreDisponible(String nombre) {
        if (marcaRepository.existsByNombreIgnoreCase(nombre)) {
            throw new RecursoDuplicadoException(
                    "Ya existe una marca con el nombre " + nombre);
        }
    }
}