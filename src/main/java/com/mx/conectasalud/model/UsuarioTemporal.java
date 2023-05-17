package com.mx.conectasalud.model;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Document("UsuariosTemporales")
public class UsuarioTemporal {
    @Id
    private String id;
    @Email
    private String correoElectronico;
    private String codigo;
    private Date createdAt;

    public void populateCreatedAt() {
        this.createdAt = Date.from(Instant.now());
    }
}
