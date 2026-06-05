package com.Cine.services;

import com.Cine.models.Asiento;
import com.Cine.repository.AsientoRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsientoServiceTest {

    static class AsientoRepositoryFake extends AsientoRepository {
        @Override
        public Asiento buscarAsiento(String letra, String numero, int idSala) {
            if (letra.equals("A") && numero.equals("1") && idSala ==1){
                Asiento asiento = new Asiento();
                asiento.setLetra("A");
                asiento.setNumero("1");
                return asiento;
            }
            return null;
        }
    }

    @Test
    void obtenerAsiento() {
        AsientoService service = new AsientoService(new AsientoRepositoryFake());

        Asiento resultado = service.obtenerAsiento("A","1",1);

        assertNotNull("A", "Deberia encontrar el asiento A1 de la sala 1");
        assertEquals("A", resultado.getLetra());
        assertEquals("1", resultado.getNumero());
    }

    @Test
    void obtenerAsientoNoExiste(){
        AsientoService service = new AsientoService(new AsientoRepositoryFake());

        Asiento resultado = service.obtenerAsiento("Z", "99",1);

        assertNull(resultado, "No debería encontrar el asiento Z-99");
    }
}