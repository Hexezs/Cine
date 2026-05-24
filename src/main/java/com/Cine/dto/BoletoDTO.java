package com.Cine.dto;

public record BoletoDTO(
        String nombreAsiento,
        int cantidad,
        int monto,
        int idAsiento
) {}