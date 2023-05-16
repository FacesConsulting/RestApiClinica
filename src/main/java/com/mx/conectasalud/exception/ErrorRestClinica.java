package com.mx.conectasalud.exception;

import com.mx.conectasalud.utils.EnumSeverity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRestClinica {
    private EnumSeverity severity;
    private String title;
    private String message;
}
