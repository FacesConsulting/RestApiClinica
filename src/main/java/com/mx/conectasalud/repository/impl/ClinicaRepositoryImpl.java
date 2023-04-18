package com.mx.conectasalud.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mx.conectasalud.model.Clinica;
import com.mx.conectasalud.repository.ClinicaRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ClinicaRepositoryImpl implements ClinicaRepository{
	
	private MongoOperations mongoOperations;

	@Override
	public List<Clinica> findAll() {
		return this.mongoOperations.find(new Query(), Clinica.class);
	}
	
	public Optional<Clinica> findOne(String nombre) {
		Clinica d = this.mongoOperations.findOne(new Query(Criteria.where("nombre").is(nombre)), Clinica.class);
        Optional<Clinica> clinica = Optional.ofNullable(d);
        return clinica;
    }

	@Override
	public Clinica saveClinica(Clinica clinica) {
		this.mongoOperations.save(clinica);
        return findOne(clinica.getId()).get();
	}

	@Override
	public void updateClinica(Clinica clinica) {
        this.mongoOperations.save(clinica);
    }

	@Override
	public void deleteClinica(String nombre) {
		this.mongoOperations.findAndRemove(new Query(Criteria.where("nombre").is(nombre)), Clinica.class);
	}

}
