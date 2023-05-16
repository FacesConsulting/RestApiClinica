package com.mx.conectasalud.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mx.conectasalud.exception.CustomException;
import com.mx.conectasalud.model.EncryptedData;
import com.mx.conectasalud.model.JsonStringify;
import com.mx.conectasalud.service.ClinicaService;
import com.mx.conectasalud.utils.EnumSeverity;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/clinica")
public class ClinicaController {

    private ClinicaService clinicaService;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> registroClinica(@RequestParam(value = "logo", required = false) MultipartFile logo,
            @RequestParam("data") String data, @RequestParam("key") String key, @RequestParam("iv") String iv) {
        try {
            clinicaService.registroClinica(logo, data, key, iv);
        } catch (CustomException e) {
            log.info("Ocurrio un error: {} ", e.getMessage());
            return ResponseEntity.internalServerError().body(JsonStringify.parseAlert("Lo sentimos", EnumSeverity.ERROR,
                    "Ocurrio un error inesperado, intenta nuevamente más tarde."));
        }

        return ResponseEntity.status(201).body(JsonStringify.parseAlert("Hecho", EnumSeverity.SUCCESS,
                "Clinica registrada correctamente."));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "teamInvitation")
    public ResponseEntity<Object> teamInvitation(@RequestBody @Valid EncryptedData encryptedData) {
        try {
            log.info("Recibiendo peticion para invitacion");
            clinicaService.sendMailTeamInvitation(encryptedData);
        } catch (Exception e) {
            log.info("Ocurrio un error: {} ", e.getMessage());
            return ResponseEntity.internalServerError().body(JsonStringify.parseAlert("Lo sentimos", EnumSeverity.ERROR,
                    "Ocurrio un error inesperado, intenta nuevamente más tarde."));
        }
        return ResponseEntity.ok().body(JsonStringify.parseAlert("Mensaje Enviado", EnumSeverity.SUCCESS,
                "Se te envio un correo electrónico a tu buzón, para continuar con el procedimiento."));
    }
}
