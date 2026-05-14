package com.Cine.services;

import com.Cine.dto.TipoSalaDTO;
import com.Cine.mapper.TipoSalaMapper;
import com.Cine.models.TipoSala;
import com.Cine.repository.TipoSalaRepository;

import java.util.ArrayList;
import java.util.List;

public class TipoSalaService {
    private TipoSalaRepository tipoSalaBD = new TipoSalaRepository();

    public List<TipoSalaDTO> obtenerTodo() {
        List<TipoSalaDTO> tipoSalaDTOS = new ArrayList<>();

        for (TipoSala tipo : tipoSalaBD.getAllTipos()) {
            tipoSalaDTOS.add(TipoSalaMapper.aDTO(tipo));
        }
        return tipoSalaDTOS;
    }
}

