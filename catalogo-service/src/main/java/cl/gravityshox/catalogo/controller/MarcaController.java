package cl.gravityshox.catalogo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.gravityshox.catalogo.dto.MarcaRequest;
import cl.gravityshox.catalogo.dto.MarcaResponse;
import cl.gravityshox.catalogo.service.MarcaService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaResponse>> listar(){
        return ResponseEntity.ok(marcaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(marcaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<MarcaResponse> crear(@Valid @RequestBody MarcaRequest request){
        MarcaResponse creada = marcaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody MarcaRequest request){
        return ResponseEntity.ok(marcaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        marcaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}