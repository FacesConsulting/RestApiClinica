package com.mx.conectasalud.utils;

import java.util.regex.Pattern;

public enum EnumRegex {
    RAZON_SOCIAL(".*"),
    RFC("^([A-ZÑ&]{3,4})(\\d{6})((\\D|\\d){3})?$"),
    EMAIL("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"),
    PASSWORD("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$"),
    TELEFONO("^\\d{10}$"),
    CODIGO_POSTAL("^\\d{5}$"),
    ESTADO("^[a-zA-Z]+$"),
    MUNICIPIO("^[a-zA-Z]+$"),
    COLONIA("^[a-zA-Z0-9]+$"),
    CALLE("^[a-zA-Z0-9]+$"),
    NUMERO_EXTERIOR("^[0-9]+$"),
    NUMERO_INTERIOR("^[0-9]+$")
    ;

    private final String regex;

    EnumRegex(String regex){
        this.regex = regex;
    }

    public Pattern getPattern(){
        return Pattern.compile(regex);
    }

}
