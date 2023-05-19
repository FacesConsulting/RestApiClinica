package com.mx.conectasalud.utils;

import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.mx.conectasalud.jwt.JWTGenerator;
import com.tinify.Tinify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    private Utils() {
    }

    private static Random random = new Random();

    public static String generateRandomCode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public static String verifyToken(String id) {
        try {
            JWTGenerator jwt = new JWTGenerator();
            Map<String, String> mapa = new HashMap<>();
            mapa.put("id_temporal", id);
            return jwt.generateJWT(mapa);
        } catch (Exception e) {
            return null;
        }

    }

    public static byte[] imageProcessing(MultipartFile file) {
        if (file == null) {
            return new byte[0];
        }

        try {
            byte[] souceData = file.getBytes();
            return Tinify.fromBuffer(souceData).toBuffer();
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public static boolean validarExpresionRegular(String cadena, String regex) {
        Pattern patron = Pattern.compile(regex);
        Matcher matcher = patron.matcher(cadena);
        return matcher.matches();
    }

}
