package com.mx.conectasalud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.conectasalud.model.Clinica;
import com.mx.conectasalud.repository.ClinicaRepository;
import com.mx.conectasalud.service.ClinicaService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ClinicaServiceImpl implements ClinicaService{
	
	private ClinicaRepository clinicaRepository;

	@Override
	public List<Clinica> findAll() {
		log.info("Obteniendo todas las clinicas");
		return clinicaRepository.findAll();
	}

	@Override
	public Clinica saveClinica(Clinica clinica) {
		log.info("Almacenando clinica");
		return clinicaRepository.saveClinica(clinica);
	}

	@Override
	public void updateClinica(Clinica clinica) {
		log.info("Actualizando clinica");
		clinicaRepository.updateClinica(clinica);
	}

	@Override
	public void deleteClinica(String nombre) {
		log.info("Eliminando clinica");
		clinicaRepository.deleteClinica(nombre);
	}

}
