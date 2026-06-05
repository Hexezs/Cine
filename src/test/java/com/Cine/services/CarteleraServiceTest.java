package com.Cine.services;

import com.Cine.models.Sala;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CarteleraServiceTest {
    private CarteleraService c;

    @BeforeEach
    void setUp(){
        c = new CarteleraService();
    }

//    @Test
//    void guardarFuncion() {
//    }

    @Test
    @DisplayName("!Existe")
    void validarDuracionFuncion() {
        Sala salaPrueba = new Sala();
        salaPrueba.setIdsala(3);

        LocalDate fecha = LocalDate.of(2026,5,21);

        boolean resultado = c.validarDuracionFuncion(salaPrueba, fecha, "16:00", 60);

        assertFalse(resultado, "No hay conflicto");
        //se refiere a que no existe una ya en ese horario en esa fecha
    }

    @Test
    @DisplayName("No consecutivo")
    void DuracionFuncionConsecutivaTest(){
        Sala salaPrueba = new Sala();
        salaPrueba.setIdsala(3);
        LocalDate fecha = LocalDate.of(2026,5,21);

        boolean resultado = c.validarDuracionFuncion(salaPrueba, fecha, "17:00",60);

        assertFalse(resultado, "No se pueden dos peliculas seguidas");
    }
}