package com.Cine.services;

import com.Cine.models.Asiento;
import com.Cine.repository.AsientoRepository;

public class AsientoService {

    private final AsientoRepository asientoRepository;

    public AsientoService() {                                  // tu app
        this.asientoRepository = new AsientoRepository();
    }
    public AsientoService(AsientoRepository asientoRepository) {  // el test
        this.asientoRepository = asientoRepository;
    }

    public Asiento obtenerAsiento(String letra, String numero, int idSala) {
        return asientoRepository.buscarAsiento(letra, numero, idSala);
    }
}