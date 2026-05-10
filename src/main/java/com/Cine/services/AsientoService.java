package com.Cine.services;

import com.Cine.models.Asiento;

import java.util.List;
import java.util.Optional;

public class AsientoService {
    private final AsientoRepository asientoRepository;

    public AsientoService(AsientoRepository asientoRepository) {
        this.asientoRepository = asientoRepository;
    }

    public Asiento guardarAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    public List<Asiento> obtenerTodosLosAsientos() {
        return asientoRepository.findAll();
    }

    public Optional<Asiento> obtenerAsientoPorId(int id) {
        return asientoRepository.findById(id);
    }

    public Asiento actualizarAsiento(int id, Asiento asientoActualizado) {
        return asientoRepository.findById(id)
                .map(asientoExistente -> {
                    asientoExistente.setNumero(asientoActualizado.getNumero());
                    asientoExistente.setLetra(asientoActualizado.getLetra());
                    return asientoRepository.save(asientoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Error: El asiento con ID " + id + " no existe."));
    }

    public void eliminarAsiento(int id) {
        asientoRepository.deleteById(id);
    }
}
