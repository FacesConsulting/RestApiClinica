package com.mx.conectasalud.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mx.conectasalud.exception.RequestException;
import com.mx.conectasalud.model.Clinica;
import com.mx.conectasalud.model.Usuario;
import com.mx.conectasalud.model.UsuarioTemporal;
import com.mx.conectasalud.repository.ClinicaRepository;
import com.mx.conectasalud.utils.EnumSeverity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class ClinicaRepositoryImpl implements ClinicaRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static final String CORREO_ELECTRONICO = "correoElectronico";
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

	@Override
	public String saveUser(Usuario usuario) {
		Query query = new Query();
		query.addCriteria(Criteria.where(CORREO_ELECTRONICO).is(usuario.getCorreoElectronico().toLowerCase()));
		if (mongoTemplate.exists(query, Usuario.class)) {
			throw new RequestException(HttpStatus.CONFLICT, "Alerta", EnumSeverity.WARNING,
					"El correo electrónico proporcionado ya ha sido dado de alta anteriormente");
		}
		return mongoTemplate.insert(usuario).getId();
	}

	@Override
	public void saveClinic(Clinica clinica) {
		log.info("Se guardo como clinica");
		mongoTemplate.insert(clinica);
	}

	@Override
	public String saveTemporaryUser(UsuarioTemporal usuarioTemporal) {
		Query query = new Query();
		query.addCriteria(Criteria.where(CORREO_ELECTRONICO).is(usuarioTemporal.getCorreoElectronico().toLowerCase()));
		if (mongoTemplate.exists(query, UsuarioTemporal.class)) {
			throw new RequestException(HttpStatus.CONFLICT, "Alerta", EnumSeverity.WARNING,
					"El correo electrónico proporcionado ya ha sido dado de alta anteriormente.");
		}
		mongoTemplate.indexOps(UsuarioTemporal.class).ensureIndex(new Index().on("createdAt", Sort.Direction.ASC).expire(3600));
		usuarioTemporal.populateCreatedAt();
		return mongoTemplate.insert(usuarioTemporal).getId();
	}
}
