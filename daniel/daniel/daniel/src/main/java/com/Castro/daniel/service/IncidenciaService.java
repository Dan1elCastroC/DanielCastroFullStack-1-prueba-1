package com.Castro.daniel.service;

import com.Castro.daniel.model.Incidencia;
import com.Castro.daniel.repository.IncidenciaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Castro.daniel.exception.model.ResourceNotFoundException;

@Service
public class IncidenciaService {
    
    private final IncidenciaRepository repository;

    public IncidenciaService(IncidenciaRepository repository) {
        this.repository = repository;
    }

    public List<Incidencia> getAll() {
        return repository.getIncidencias();
    }

    public Incidencia getById(int id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia con ID " + id + " no encontrada."));
    }

    public Incidencia create(Incidencia incidencia) {
        return repository.agregarIncidencia(incidencia);
    }

    public Incidencia update(int id, Incidencia actualizada) {
        this.getById(id);
        actualizada.setId(id);
        return repository.actualizar(actualizada);
    }

    public void delete(int id) {
        this.getById(id);
        repository.eliminar(id);
    }

    public int getTotalCount() {
        return repository.totalIncidencias();
    }

    public List<Incidencia> getByEstado(String estado) {
        return repository.buscarPorEstado(estado);
    }
}