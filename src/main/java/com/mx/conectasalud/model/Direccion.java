package com.mx.conectasalud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Direccion {

    private String codigoPostal;

    private String estado;

    private String municipio;

    private String colonia;

    private String calle;

    private String numeroExterior;

    private String numeroInterior;
}
