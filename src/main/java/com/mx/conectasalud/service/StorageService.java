package com.mx.conectasalud.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    
    String store(String base64Data) throws IOException;

    Resource loadAsResource(String filename);
}
