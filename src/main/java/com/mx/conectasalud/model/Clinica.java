package com.mx.conectasalud.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("Clinicas")
public class Clinica {

	@Id
	private String id;
	@NotBlank
    private String usuarioId;
	@NotBlank
	private String razonSocial;
	@NotBlank
	private String rfc;
	@NotBlank
	private Direccion direccion;
	@NotBlank
	private String telefono;
	private ArrayList<Puntuacion> listaPuntuacion; 
}
