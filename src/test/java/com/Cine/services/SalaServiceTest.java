package com.Cine.services;

import com.Cine.models.Sala;
import com.Cine.repository.SalaRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaServiceTest {

    static class SalaRepositoryFake extends SalaRepository{
        @Override
        public Sala getSalaByID(int id){
            if (id == 1){
                Sala sala = new Sala();
                sala.setCapacidad(50);
                return sala;
            }
            return null;
        }
    }

    @Test
    void agregarSala() {
        SalaService service = new SalaService(new SalaRepositoryFake());

        Sala resultado = service.buscarPorId(1);

        assertNotNull(resultado, "Debeía encontrar la sala 1");
        assertEquals(50, resultado.getCapacidad());
    }


    @Test
    void buscarPorId() {
        SalaService service = new SalaService(new SalaRepositoryFake());
        Sala resultado = service.buscarPorId(999);

        assertNull(resultado, "No debería encontrar la sala 999");
    }
}