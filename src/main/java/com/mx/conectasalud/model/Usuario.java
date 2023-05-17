package com.mx.conectasalud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("Usuario")
public class Usuario {

    @Id
    private String id;
    @NotBlank
    @Size(max = 50)
    @Email
    private String correoElectronico;
    @Size(min = 8, max = 15)
    private String password;
    private String rol;
    private String plataforma;
    private String token;
    private String codigo;
    private boolean terminos;
    private boolean servicios;

}
