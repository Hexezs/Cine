package com.Cine;

import com.Cine.dto.UsuarioInicioDTO;
import com.Cine.dto.UsuarioRegistroDTO;
import com.Cine.models.Usuario;
import com.Cine.services.CarteleraService;
import com.Cine.services.PeliculaService;
import com.Cine.services.UsuarioService;
import com.Cine.utils.HibernateUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Inicio_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
//        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("CineSyncNoFondoLogo.png")));
        stage.setTitle("CineSync");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        HibernateUtils.closeEntityManagerFactory();
        super.stop();
    }

    public static void main(String[] args) {
//      holiiis
//        UsuarioService usuarioService = new UsuarioService();
//        PeliculaService peliculaService = new PeliculaService();
//        CarteleraService carteleraService = new CarteleraService();
//        System.out.println("Prueba 1");
//        UsuarioRegistroDTO registroDTO = new UsuarioRegistroDTO(
//                "Aura", "Morales", "Romero", "prueba1@gmail", "1234"
//        );
//
//        System.out.println("Registrando usuario...");
//        Usuario usuarioRegistrado = usuarioService.registrarNuevoUsuario(registroDTO);
//        System.out.println("Usuario ID: " + usuarioRegistrado.getIdusuario());
//        System.out.println("Inicio Usuario");
//        UsuarioInicioDTO loginDTO = new UsuarioInicioDTO("prueba1@gmail.com", "1234", "Cliente");
//
//        usuarioService.iniciarSesion(loginDTO).ifPresentOrElse(
//                user -> System.out.println("Inicio de sesion de : " + user.getNombre()),
//                () -> System.out.println("Error :c")


//        );
//
//        System.out.println("Consultando cartelera");
//        peliculaService.obtenerPeliculas().forEach(p-> {
//            System.out.println("Pelicula | " + p.getNombre() + " | Duracion " + p.getTiempo() + " min");
//        });

        launch();
    }
}