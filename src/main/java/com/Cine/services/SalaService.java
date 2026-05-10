package com.Cine.services;

import com.Cine.models.Sala;

import java.util.List;
import java.util.Optional;

public class SalaService {
    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository){
        this.salaRepository = salaRepository;
    }

    //CRUD Create
    public Sala guardarSala(Sala sala){
        this.salaRepository = salaRepository;
    }

    //                                              Read (todas)
    public List<Sala> obtenerTodasLasSalas(){
        return salaRepository.findAll();
    }

    //CRUD Read (por id)
    public Optional<Sala> obtenerSalaPorId(int id){
        return salaRepository.findById(id);
    }

    //CRUD Update
    public Sala actualizarSala(int id, Sala salaActualizada){
        return salaRepository.findById(id).map(salaExistente ->{
            salaExistente.setCapacidad(salaActualizada.getCapacidad());
            salaExistente.setHorario(salaActualizada.getHorario());
            salaExistente.setDisponibles(salaActualizada.getDisponibles());
            //actualizar claves foraneas
            salaExistente.setIdPelicula(salaActualizada.getIdPelicula());
            salaExistente.setIdTipoSala(salaActualizada.getIdTipoSala());
            salaExistente.setIdAsiento(salaActualizada.getIdAsiento());
        });
    }

}
