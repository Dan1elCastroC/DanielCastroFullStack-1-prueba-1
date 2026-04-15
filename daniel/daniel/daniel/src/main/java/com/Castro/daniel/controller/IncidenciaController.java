package com.Castro.daniel.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.Castro.daniel.model.Incidencia;
import com.Castro.daniel.service.IncidenciaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
@Slf4j
@Validated
public class IncidenciaController {

    private final IncidenciaService service;

    @GetMapping 
    public ResponseEntity<List<Incidencia>> obtenerTodas() {
        log.info("GET /api/incidencias - Mostrando todas las incidencias");
        List<Incidencia> lista = service.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerPorId(
            @PathVariable @Min(value = 1, message = "El ID debe ser un número positivo") int id) {
        
        log.info("GET /api/incidencias/{} - Buscando incidencia por ID", id);
        Incidencia incidencia = service.getById(id);
        return ResponseEntity.ok(incidencia);
    }

    @PostMapping
    public ResponseEntity<Incidencia> create(@Valid @RequestBody Incidencia incidencia) {
        log.info("POST /api/incidencias - Crear nueva incidencia", incidencia);
        Incidencia nueva = service.create(incidencia);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incidencia> actualizar(
        @PathVariable @Min(1) int id,
        @Valid @RequestBody Incidencia incidenciaActualizada) {
        
        log.info("PUT /api/incidencias/{} - Actualizando incidencia", id);
        Incidencia incidencia = service.update(id, incidenciaActualizada);
        return ResponseEntity.ok(incidencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
        @PathVariable @Min(1) int id) {
        
        log.info("DELETE /api/incidencias/{} - Eliminando incidencia", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> contarIncidencias() {
        return ResponseEntity.ok(service.getTotalCount());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Incidencia>> buscarPorEstado(@PathVariable String estado) {
        log.info("GET /api/incidencias/estado/{} - Buscando incidencias por estado", estado);
        List<Incidencia> lista = service.getByEstado(estado);
        return ResponseEntity.ok(lista);
    }
}