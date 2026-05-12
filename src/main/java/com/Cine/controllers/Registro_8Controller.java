package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
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
    public void initialize() {
        SharedData data = SharedData.getInstance();
        if (data.getPeliculaSeleccionada() != null) {
            Text peliText = new Text(data.getPeliculaSeleccionada().getNombre());
            NomPeli.getChildren().add(peliText);
        }

        if (data.getBoletosTemporales() != null && !data.getBoletosTemporales().isEmpty()) {
            String asientosStr = data.getBoletosTemporales().stream()
                    .map(b -> b.getNombreasiento())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");

            Text asientoText = new Text(asientosStr);
            Asiento.getChildren().add(asientoText);
        }
        try {
            int cantidad = data.getCantidadBoletos();
            double precioBoleto = 65.0;
            double total = cantidad * precioBoleto;

            Text montoText = new Text("$" + total + " MXN");
            MontoPagar.getChildren().add(montoText);
        } catch (Exception e) {
            MontoPagar.getChildren().add(new Text("$0.00"));
        }
    }

    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException {
        // Antes de irnos, limpiamos la selección actual para que
        // el usuario pueda hacer una compra nueva desde cero si quiere
        SharedData.getInstance().limpiarReserva();

        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Seleccionar Pelicula");
        stage.setScene(nextScene);
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
}