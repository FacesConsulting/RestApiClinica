package com.mx.conectasalud.configuration;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CouldinaryConfig {
    // @Value("${cloudinary.api.cloud_name}")
    private String cloudName = "storageconsultaya";
    // @Value("${cloudinary.api.key}")
    private String key = "946294516624134";
    // @Value("${cloudinary.api.secret}")
    private String secret = "i7xijrjqBndOxZRf9UXKaXNshNo";

    @Bean
    public Cloudinary cloudinaryConfig() {
      return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", key, "api_secret", secret));
       
    }
}
