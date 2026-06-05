package com.Cine.services;

import com.Cine.models.Sala;
import com.Cine.repository.SalaRepository;
import java.util.List;

public class SalaService {
    private final SalaRepository salaRepository;

    public SalaService() {                          // tu app usa este
        this.salaRepository = new SalaRepository();
    }

    public SalaService(SalaRepository salaRepository) {  // el test usa este
        this.salaRepository = salaRepository;
    }

    public void agregarSala(Sala sala){
        salaRepository.saveSala(sala);
    }

    public List<Sala> obtenerSalas(){
        return salaRepository.getAllSalas();
    }

    public Sala buscarPorId(int idSala){
        return salaRepository.getSalaByID(idSala);
    }
}