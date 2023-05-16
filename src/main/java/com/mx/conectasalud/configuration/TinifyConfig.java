package com.mx.conectasalud.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.tinify.Tinify;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "app")
public class TinifyConfig {

    private String tinifyApiKey;

    @PostConstruct
    public void init() {
        Tinify.setKey(tinifyApiKey);
    }
}
