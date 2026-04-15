package com.Castro.daniel.repository;

import com.Castro.daniel.model.Incidencia;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class IncidenciaRepository {
    
    private List<Incidencia> listaIncidencias = new ArrayList<>();

    public IncidenciaRepository() {
        listaIncidencias.add(new Incidencia(1, "Fallo en inicio de sesión", "Pendiente", "Alta", "admin", "2023-10-01"));
        listaIncidencias.add(new Incidencia(2, "Error al cargar la página", "En proceso", "Media", "user1", "2023-10-02"));
        listaIncidencias.add(new Incidencia(3, "Botón no responde", "Resuelto", "Baja", "user2", "2023-10-03"));
    }

    public List<Incidencia> getIncidencias() {
        return new ArrayList<>(listaIncidencias);
    }

    public Optional<Incidencia> buscarPorId(int id) {
        return listaIncidencias.stream()
                .filter(i -> i.getId() == id)
                .findFirst();
    }

    public Incidencia agregarIncidencia(Incidencia nueva) {
        if(buscarPorId(nueva.getId()).isPresent()) {
            throw new IllegalArgumentException("La incidencia con ID " + nueva.getId() + " ya existe.");
        }
        listaIncidencias.add(nueva);
        return nueva;
    }

    public Incidencia actualizar(Incidencia actualizada) {
        Optional<Incidencia> existente = buscarPorId(actualizada.getId());

        if(existente.isPresent()) {
            Incidencia inc = existente.get();
            inc.setDescripcion(actualizada.getDescripcion());
            inc.setEstado(actualizada.getEstado());
            inc.setPrioridad(actualizada.getPrioridad());
            inc.setUsuario(actualizada.getUsuario());
            inc.setFechaRegistro(actualizada.getFechaRegistro());
        }

        return actualizada;
    }

    public void eliminar(int id) {
        listaIncidencias.removeIf(i -> i.getId() == id);
    }

    public int totalIncidencias() {
        return listaIncidencias.size();
    }

    public List<Incidencia> buscarPorEstado(String estado) {
        return listaIncidencias.stream()
                .filter(incidencia -> incidencia.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }
}