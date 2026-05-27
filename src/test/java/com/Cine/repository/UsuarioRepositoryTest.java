package com.Cine.repository;

import com.Cine.models.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UsuarioRepositoryTest {

    @Test
    void addUser() {
        // ARRANGE: preparo lo que necesito
        UsuarioRepository repo = new UsuarioRepository();

        // Uso un correo único con timestamp para no chocar con la restricción UNIQUE
        // si ya corriste el test antes
        String correoUnico = "test_" + System.currentTimeMillis() + "@cinesync.com";

        Usuario nuevo = new Usuario(
                "Test",
                "Prueba",
                "Unitaria",
                correoUnico,
                "1234",
                null  // tipoUsuario, si tu BD lo permite null
        );

        // ACT: ejecuto lo que quiero probar
        repo.addUser(nuevo);

        // ASSERT: verifico que pasó lo esperado
        // Si addUser funcionó, Hibernate le habrá asignado un ID > 0
        assertTrue(nuevo.getIdusuario() > 0,
                "Tras insertar, el usuario debería tener un ID asignado");

        // Verificación más fuerte: lo busco en la BD y confirmo que existe
        Usuario recuperado = repo.getUserByID(nuevo.getIdusuario());
        assertNotNull(recuperado, "El usuario debería existir en la BD");
        assertEquals(correoUnico, recuperado.getCorreo(),
                "El correo guardado debe coincidir con el que mandé");

        // CLEANUP (opcional pero recomendable): borro el registro de prueba
        repo.removeUser(recuperado);
    }
}
