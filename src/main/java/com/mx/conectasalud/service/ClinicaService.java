package com.mx.conectasalud.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mx.conectasalud.exception.CustomException;
import com.mx.conectasalud.model.Clinica;
import com.mx.conectasalud.model.EncryptedData;

public interface ClinicaService {

	public List<Clinica> findAll();

	public Clinica saveClinica(Clinica user);

	public void updateClinica(Clinica user);

	public void deleteClinica(String userId);

	public String registroClinica(MultipartFile logo, String data, String key, String iv ) throws CustomException;

	public void sendMailTeamInvitation(EncryptedData encryptedData) throws CustomException;
}
