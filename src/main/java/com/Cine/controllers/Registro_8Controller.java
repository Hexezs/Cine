package com.Cine.controllers;

import com.Cine.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class Registro_8Controller {
    @FXML
    private TextFlow NomPeli;
    @FXML
    private TextFlow Asiento;
    @FXML
    private TextFlow MontoPagar;
    @FXML
    private Button BtnAtras;
    @FXML
    private Button BtnSig;
    @FXML
    public void initialize(){

    }
    @FXML
    public void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/SelecPeli_7.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Seleccionar Asiento");
        stage.setScene(nextScene);
    }
    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Seleccionar Pelicula");
        stage.setScene(nextScene);
    }
}
