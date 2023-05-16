package com.mx.conectasalud.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mx.conectasalud.jwt.JWTGenerator;

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

    public static String verifyToken(String code) {
        try {
            JWTGenerator jwt = new JWTGenerator();
            Map<String, String> mapa = new HashMap<>();
            mapa.put("codigoVerificacion", code);
            return jwt.generateJWT(mapa);
        } catch (Exception e) {
            return null;
        }

    }
}
