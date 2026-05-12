package com.Cine.mapper;

import com.Cine.models.Boleto;
import com.Cine.dto.BoletoDTO;

public class BoletoMapper {
    public static BoletoDTO aDTO(Boleto entidad){
        return new BoletoDTO(entidad.getIdBoleto(),
                entidad.getCantidad(),
                entidad.getMonto(),
                entidad.getIdReserva(),
                entidad.getIdAsiento()
        );
    }

    public static Boleto aEntidad(BoletoDTO dto){
        Boleto entidad = new Boleto();
        entidad.setCantidad(dto.cantidad());
        entidad.setMonto(dto.monto());
        return entidad;
    }
}