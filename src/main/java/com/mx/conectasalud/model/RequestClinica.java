package com.mx.conectasalud.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RequestClinica {

    // Datos clinicos
    private String razonSocial;
    private String rfc;
    private MultipartFile logo;

    // Localizacion
    private String codigoPostal;
    private String estado;
    private String municipio;
    private String colonia;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;

    // Credenciales
    private String telefono;
    private String correoElectronico;
    private String password;
    private String plataforma;

    // Terminos
    private boolean terminos;
    private boolean servicios;
}
