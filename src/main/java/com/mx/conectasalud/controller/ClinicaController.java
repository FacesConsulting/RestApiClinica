package com.mx.conectasalud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.conectasalud.model.Clinica;
import com.mx.conectasalud.service.ClinicaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/clinica")
public class ClinicaController {
	
	private ClinicaService clinicaService;
	
	@PostMapping(path = "guardar")
    public  ResponseEntity<Clinica> saveUser(@RequestBody @Valid Clinica clinica){
    	log.info("Save new user");
         return ResponseEntity.ok(clinicaService.saveClinica(clinica));
    }
}
