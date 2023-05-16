package com.mx.conectasalud.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public class Puntuacion {
    @Id
    private String id;
    @NotBlank
    private float calificacion;
}
