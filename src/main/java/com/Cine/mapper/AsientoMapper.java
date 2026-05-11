package com.Cine.mapper;

import com.Cine.dto.AsientoDTO;
import com.Cine.models.Asiento;

public class AsientoMapper {

    // idSala viene de afuera porque Asiento no conoce su Sala
    public static AsientoDTO aDTO(Asiento asiento, int idSala){
        return new AsientoDTO(
                asiento.getIdAsiento(),
                asiento.getNumero(),
                asiento.getLetra(),
                idSala
        );
    }

    public static Asiento aEntidad(AsientoDTO dto){
        Asiento asiento = new Asiento();
        asiento.setNumero(dto.numero());
        asiento.setLetra(dto.letra());
        // idSala no se asigna aquí porque Asiento no tiene ese campo
        // el Service debe buscar la Sala y hacer sala.setIdAsiento(asiento)
        return asiento;
    }

}
