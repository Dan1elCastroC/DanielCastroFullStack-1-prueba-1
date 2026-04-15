package com.Castro.daniel.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidencia {
    
    private int id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El nivel de prioridad es obligatorio")
    private String prioridad;

    @NotBlank(message = "El usuario que reporta es obligatorio")
    private String usuario;

    private String fechaRegistro;
}