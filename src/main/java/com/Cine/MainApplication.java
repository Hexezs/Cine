package com.Cine;

import com.Cine.models.Direccion;
import com.Cine.models.Usuario;
import com.Cine.services.DireccionService;
import com.Cine.services.UsuarioService;
import com.Cine.utils.DateUtils;
import com.Cine.utils.HibernateUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
//HOLIS!!!!!!
public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Inicio_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Ejemplo Hibernate");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        DireccionService direccionService = new DireccionService();
//
//        Direccion direccion = new Direccion("Calle 1","Colonia 1",23400,"Los Cabos","BCS");
//        Direccion direccion2 = new Direccion("Calle 2","Colonia 2",23400,"Los Cabos","BCS");
//        direccionService.addDireccion(direccion);
//        direccionService.addDireccion(direccion2);
//
//    Usuario homero = new Usuario("Homero","J.","Simpson",48);
//        homero.setDireccion(direccion);
//        homero.setFechaUltimoInicio(Instant.now());
//        usuarioService.addUser(homero);
//
//        Usuario lisa = new Usuario("Lisa","Simpson","Lopez",10);
//        lisa.setDireccion(direccion2);
//        lisa.setFechaUltimoInicio(Instant.now());
//        usuarioService.addUser(lisa);
//
//        List<Usuario> result = usuarioService.getAllUsers();
//        for ( Usuario usuario : result ) {
//            System.out.println(usuario.getNombre());
//            System.out.println(usuario.getDireccion().toString());
//            System.out.println(DateUtils.getDate(usuario.getFechaUltimoInicio()));
//            System.out.println(DateUtils.getTime(usuario.getFechaUltimoInicio()));
//            System.out.println(DateUtils.getDateTime(usuario.getFechaUltimoInicio()));
//        }
//
//        System.out.println("=========================");
//        Usuario lisa2 = usuarioService.getUserByID(2);
//        System.out.println(lisa2.getNombre());
//        System.out.println(lisa2.getDireccion().toString());
//        lisa2.setEdad(11);
//        usuarioService.updateUser(lisa2);
//        usuarioService.removeUser(lisa2);
//        direccionService.removeDireccion(direccion2);
//
//        HibernateUtils.closeEntityManagerFactory();

        launch();
    }
}