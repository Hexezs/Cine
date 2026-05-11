package com.Cine.mapper;

import com.Cine.dto.AsientoDTO;
import com.Cine.models.Asiento;

public class AsientoMapper {

    // idSala viene de afuera porque Asiento no conoce su Sala
    public static AsientoDTO aDTO(Asiento entidad, int idSala){
        return new AsientoDTO(
                entidad.getIdAsiento(),
                entidad.getNumero(),
                entidad.getLetra(),
                idSala
        );
    }

    public static Asiento aEntidad(AsientoDTO dto){
        Asiento entidad = new Asiento();
        entidad.setNumero(dto.numero());
        entidad.setLetra(dto.letra());
        // idSala no se asigna aquí porque Asiento no tiene ese campo
        // el Service debe buscar la Sala y hacer sala.setIdAsiento(asiento)
        return entidad;
    }

}
