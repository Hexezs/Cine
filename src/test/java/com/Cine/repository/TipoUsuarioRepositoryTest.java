package com.Cine.repository;

import com.Cine.models.TipoUsuario;
import com.Cine.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioRepositoryTest {

    private TipoUsuarioRepository r;

    //iniciar repositorio
    @BeforeEach
    void setUp(){
        r = new TipoUsuarioRepository();
    }

    @Test
    @DisplayName("Buscar por id un admin")
    void getByIDTest(){
        int idAdmin = 1;

        TipoUsuario tipo = r.getTipoByID(idAdmin);

        assertNotNull(tipo,"Encuentra tipo por id");
        assertEquals(idAdmin, tipo.getIdTipoUsuario(),"Id debe coincidir");
    }

    @Test
    @DisplayName("Lista predeterinada de tipo usuario? r=yes")
    void getTiposTest(){
        List<TipoUsuario> lista = r.getAllTipos();

        assertNotNull(lista, "La lista no debe ser nula, obvi");
        assertTrue(lista.size() >= 1,"Debe haber dos tipos");
    }
}