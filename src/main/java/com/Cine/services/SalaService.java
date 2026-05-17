package com.Cine.services;

import com.Cine.dto.SalaDTO;
import com.Cine.mapper.SalaMapper;
import com.Cine.models.Sala;
import com.Cine.models.TipoSala;
import com.Cine.repository.SalaRepository;
import com.Cine.repository.TipoSalaRepository;

public class SalaService {
    private SalaRepository salaRepository;
    private TipoSalaRepository tipoSalaRepository;

    public void guardarSala(SalaDTO dto) {
        Sala nuevaSala = SalaMapper.aEntidad(dto);
        TipoSala tipoEncontrado = tipoSalaRepository.getTipoByID((dto.idTipoSala()));

        if (tipoEncontrado != null) {
            nuevaSala.setIdTipoSala(tipoEncontrado);
            salaRepository.saveSala(nuevaSala);
        }else{
            System.out.println("No se encontro tipo sala con id " + dto.idTipoSala());
        }
    }
}

