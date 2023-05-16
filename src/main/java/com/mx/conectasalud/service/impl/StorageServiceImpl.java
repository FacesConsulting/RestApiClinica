package com.mx.conectasalud.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.mx.conectasalud.configuration.TinifyConfig;
import com.mx.conectasalud.exception.RequestException;
import com.mx.conectasalud.service.StorageService;
import com.mx.conectasalud.utils.EnumSeverity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {


    private TinifyConfig config;

    @Override
    public String store(String base64Data) throws IOException {
        if(base64Data.isEmpty()){
            throw new RequestException(HttpStatus.BAD_REQUEST, "Archivo vacio", EnumSeverity.ERROR, "Lo sentimos, no se pudo almacenar el archivo ya que viene vacio.");
        }

        log.info("base64Data {}: ", base64Data);
        
        
        // String filename = file.getOriginalFilename();

        // return cloudinary.uploader()
        //                  .upload(file.getBytes(), Map.of("public_id", UUID.randomUUID().toString()))
        //                  .get("url")
        //                  .toString();

        return "";
    }

    @Override
    public Resource loadAsResource(String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadAsResource'");
    }
    
}
