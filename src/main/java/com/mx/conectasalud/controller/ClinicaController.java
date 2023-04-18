package com.mx.conectasalud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.conectasalud.service.ClinicaService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/clinica")
public class ClinicaController {
	
	private ClinicaService clinicaService;

	@GetMapping
	public String getAll() {
		log.info("find all");
		return "all";
	}

	@PostMapping
	public String create(@RequestBody String test) {
		log.info("test");
		return test;
	}
}
