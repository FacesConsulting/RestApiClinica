package com.mx.conectasalud.repository;

import java.util.List;

import com.mx.conectasalud.model.Clinica;

public interface ClinicaRepository {

	public List<Clinica> findAll();

	public Clinica saveClinica(Clinica user);

	public void updateClinica(Clinica user);

	public void deleteClinica(String userId);

}
