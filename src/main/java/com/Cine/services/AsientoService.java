package com.Cine.services;

import com.Cine.dto.AsientoDTO;
import com.Cine.mapper.AsientoMapper;
import com.Cine.models.Asiento;
import com.Cine.repository.AsientoRepository;

import java.util.ArrayList;
import java.util.List;

public class AsientoService {
    private AsientoRepository asientoBD = new AsientoRepository();
    public List<AsientoDTO> getAsientosPorSala(int idSala){
        List<AsientoDTO> asientoDTOs = new ArrayList<>();
        for(Asiento asiento : asientoBD.getAsientosBySala(idSala)){
            asientoDTOs.add(AsientoMapper.aDTO(asiento, idSala));
        }
        return asientoDTOs;
    }
}
