package com.Cine.mapper;

import com.Cine.dto.BoletoDTO;
import com.Cine.models.Asiento;
import com.Cine.models.Boleto;
import com.Cine.models.Reserva;

public class BoletoMapper {

    // 🔥 DTO simple (solo datos base)
    public static Boleto aEntidad(BoletoDTO dto, Asiento asientoBD, Reserva reservaBD) {

        Boleto entidad = new Boleto();

        entidad.setCantidad(dto.cantidad());
        entidad.setMonto(dto.monto());
        entidad.setNombreasiento(dto.nombreAsiento());

        // 🔥 relaciones Hibernate correctas
        entidad.setIdasiento(asientoBD);
        entidad.setIdReserva(reservaBD);

        return entidad;
    }

    // 🔥 Entity → DTO (sin romper Hibernate)
    public static BoletoDTO aDTO(Boleto entidad) {

        return new BoletoDTO(
                entidad.getNombreasiento(),
                entidad.getCantidad(),
                entidad.getMonto(),
                entidad.getIdasiento().getIdAsiento() // mejor ID, no letra
        );
    }
}