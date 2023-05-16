package com.mx.conectasalud.model;

import com.mx.conectasalud.utils.EnumSeverity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAlert {
    private EnumSeverity severity;
    private String title;
    private String message;
}
