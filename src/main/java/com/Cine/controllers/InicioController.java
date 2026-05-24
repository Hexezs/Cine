package com.Cine.controllers;
import com.Cine.MainApplication;
import com.Cine.models.Pelicula;
import com.Cine.services.PeliculaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class InicioController {
    @FXML
    private Button BtnSesion;

    @FXML
    private Button BtnRregistro;

    @FXML
    private FlowPane FlowCartelera;

    private final PeliculaService peliculaService = new PeliculaService();

    @FXML
    public void initialize() {
        cargarCartelera();
    }

    private void cargarCartelera() {
        List<Pelicula> peliculas = peliculaService.obtenerPeliculasConCartelera();

        for (Pelicula p : peliculas) {
            VBox card = new VBox();
            card.setSpacing(5);
            card.getStyleClass().add("card-pelicula-vista"); // toma el estilo del CSS

            ImageView imgView = new ImageView(
                    new Image(new ByteArrayInputStream(p.getImagen()))
            );
            imgView.setFitWidth(120);
            imgView.setFitHeight(160);
            imgView.setPreserveRatio(true);

            Label nombre = new Label(p.getNombre());

            card.getChildren().addAll(imgView, nombre);

            FlowCartelera.getChildren().add(card);
        }
    }

    @FXML
    private void BtnSesionAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());
        stage.setTitle("CineSync -Iniciar Sesión");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnRegistroAction(ActionEvent actionEvent) throws IOException{
        Scene scene3 = ((Button) actionEvent.getSource()).getScene();
        Stage stage3 = (Stage) scene3.getWindow();

        FXMLLoader fxmlLoader3 = new FXMLLoader(MainApplication.class.getResource("views/CreaCuenta_3.fxml"));

        Scene nextScene = new Scene(fxmlLoader3.load());
        stage3.setTitle("CineSync -Crear Cuenta");
        stage3.setScene(nextScene);
    }
}
