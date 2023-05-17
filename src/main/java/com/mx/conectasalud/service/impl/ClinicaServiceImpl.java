package com.mx.conectasalud.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mx.conectasalud.exception.CustomException;
import com.mx.conectasalud.model.Clinica;
import com.mx.conectasalud.model.Direccion;
import com.mx.conectasalud.model.EncryptedData;
import com.mx.conectasalud.model.Usuario;
import com.mx.conectasalud.model.UsuarioTemporal;
import com.mx.conectasalud.repository.ClinicaRepository;
import com.mx.conectasalud.service.ClinicaService;
import com.mx.conectasalud.service.StorageService;
import com.mx.conectasalud.utils.Utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ClinicaServiceImpl implements ClinicaService {

	private ClinicaRepository clinicaRepository;
	private StorageService storageService;
	private JavaMailSender mailSender;

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

	@Override
	public void registroClinica(MultipartFile logo, String data, String key, String iv) throws CustomException {
		log.info("Registrando clinica");

		try {

			log.info("data {}", data);
			log.info("key {}", key);
			log.info("iv {}", iv);

			String dataDecrypt = EncryptedData.decryptData(data, key, iv);

			Gson g = new Gson();
			JsonObject clinicaData = g.fromJson(dataDecrypt, JsonObject.class);
			// Obtenemos los parametros necesarios para insertar en la tabla usuarios
			String correo = clinicaData.get("correoElectronico").getAsString();
			String password = clinicaData.get("password").getAsString();
			String plataforma = clinicaData.get("plataforma").getAsString();
			boolean terminos = clinicaData.get("terminos").getAsBoolean();
			boolean servicios = clinicaData.get("politicas").getAsBoolean();

			Usuario usuarioClinica = new Usuario();

			usuarioClinica.setCorreoElectronico(correo);
			usuarioClinica.setPassword(password);
			usuarioClinica.setPlataforma(plataforma);
			usuarioClinica.setTerminos(terminos);
			usuarioClinica.setServicios(servicios);
			usuarioClinica.setRol("clinica");

			String idClinica = clinicaRepository.saveUser(usuarioClinica);

			// Obtenemos los parametros necesarios para insertar en la tabla clinicas
			String razonSocial = clinicaData.get("razonSocial").getAsString();
			String rfc = clinicaData.get("rfc").getAsString();
			String telefono = clinicaData.get("telefono").getAsString();

			// Obtenemos los parametros necesarios para crear una direccion
			String codigoPostal = clinicaData.get("codigoPostal").getAsString();
			String estado = clinicaData.get("estado").getAsString();
			String municipio = clinicaData.get("municipio").getAsString();
			String colonia = clinicaData.get("colonia").getAsString();
			String calle = clinicaData.get("calle").getAsString();
			String numeroExterior = clinicaData.get("numeroExterior").getAsString();
			String numeroInterior = clinicaData.get("numeroInterior").getAsString();

			Direccion direccion = new Direccion(codigoPostal, estado, municipio, colonia, calle, numeroExterior,
					numeroInterior);

			byte[] logoBytes = Utils.imageProcessing(logo);

			Clinica clinica = new Clinica();
			clinica.setId(idClinica);
			clinica.setRazonSocial(razonSocial);
			clinica.setRfc(rfc);
			clinica.setDireccion(direccion);
			clinica.setTelefono(telefono);
			clinica.setLogo(logoBytes);

			clinicaRepository.saveClinic(clinica);

		} catch (NullPointerException e) {
			// TODO: handle exception
			log.error("Excepcion encontrada {}", e);
			throw new CustomException(e.getMessage(), e.getCause());
		}
	}

	public void sendMailTeamInvitation(EncryptedData encryptedData) throws CustomException {

		String data = encryptedData.getData();
		String key = encryptedData.getKey();
		String iv = encryptedData.getIv();

		try {

			String serializeData = EncryptedData.decryptData(data, key, iv);

			String code = Utils.generateRandomCode();

			Gson g = new Gson();
			JsonObject jwtString = g.fromJson(serializeData, JsonObject.class);
			String email = jwtString.get("correo").getAsString();

			UsuarioTemporal usuario = new UsuarioTemporal();
			usuario.setCodigo(code);
			usuario.setCorreoElectronico(email);

			String id = clinicaRepository.saveTemporaryUser(usuario);

			String siteURL = "http://localhost:3000/auth";
			String toAddress = email;
			String fromAddress = "consultayaadmin@consulta-ya.com.mx";
			String senderName = "Consulta Ya";
			String subject = "¡Únete a nuestro equipo de trabajo!";
			String content = "Estimado/a.<br>"
					+ "<p>Me pongo en contacto contigo en nombre de Consulta Ya! para invitarte a formar parte de nuestro equipo de colaboradores. Estamos buscando clínicas y proveedores de servicios médicos de alta calidad para ofrecer a nuestros clientes la mejor atención y servicio posible, y creemos que tu clínica podría ser una excelente adición a nuestra red de colaboradores.</p>"
					+ "<p>Nos hemos informado acerca de los servicios que ofrece tu clínica y nos parece que son de excelente calidad. Por esta razón, queremos ofrecerte la oportunidad de unirte a nuestro equipo de colaboradores, donde tendrás la oportunidad de trabajar con un grupo selecto de profesionales altamente capacitados y comprometidos con la calidad y la excelencia en el servicio.</p>"
					+ "<p>Al convertirte en uno de nuestros colaboradores, podrás ofrecer tus servicios a una amplia gama de clientes, y obtener una mayor visibilidad y exposición en el mercado. Además, tendrás acceso a una variedad de beneficios, que incluyen una mayor cantidad de pacientes, un sistema de pago justo y transparente, la posibilidad de trabajar con un grupo selecto de profesionales altamente capacitados, etc.</p>"
					+ "<p>Si estás interesado/a en formar parte de nuestro equipo de colaboradores, por favor háznoslo saber lo antes posible. Cualquier duda puedes enviar un mensaje a <a href='mailto:contacto@consula-ya.com.mx?subject=Aclaración de dudas'>contacto@consulta-ya.com.mx .</a></p>"
					+ "<p>Esperamos contar con tu colaboración para poder brindar a nuestros clientes los mejores servicios médicos posibles.</p>"
					+ "<h3><a href=\"[[URL]]\" target=\"_self\">Crear cuenta</a></h3> <br/><br/>"
					+ "<center><h1>[[CODE]]</h1></center>"
					+ "<center><h2>Código para crear tu cuenta</h2></center> <br/><br/>"
					+ "Atentamente,<br>"
					+ "Consulta Ya.";

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setFrom(fromAddress, senderName);
			helper.setTo(toAddress);
			helper.setSubject(subject);

			

			String verifyURL = siteURL + "/registerClinic?code=" + Utils.verifyToken(code, email) + "&id=" + id;

			content = content.replace("[[URL]]", verifyURL);
			content = content.replace("[[CODE]]", code);

			helper.setText(content, true);

			mailSender.send(message);

		} catch (MessagingException | UnsupportedEncodingException e) {
			log.info("Ocurrio una exceptcion al enviar el codigo en el service {}", e.getMessage());
			throw new CustomException("Ocurrio una exceptcion al enviar el codigo en el service", e.getCause());
		}
	}

}
