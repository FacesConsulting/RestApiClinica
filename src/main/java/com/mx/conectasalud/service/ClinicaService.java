package com.mx.conectasalud.service;

import java.util.List;

import com.mx.conectasalud.model.Clinica;

public interface ClinicaService {

	public List<Clinica> findAll();

	public Clinica saveClinica(Clinica user);

	public void updateClinica(Clinica user);

	public void deleteClinica(String userId);
}
