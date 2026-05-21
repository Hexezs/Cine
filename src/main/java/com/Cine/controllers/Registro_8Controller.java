package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.models.Boleto;
import com.Cine.models.Cartelera;
import com.Cine.models.Reserva;
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
    private TextFlow regPeliculaNombre;
    @FXML
    private TextFlow regAsientos;
    @FXML
    private TextFlow MontoPagar;
    @FXML
    private TextFlow regSala;
    @FXML
    private TextFlow regHorario;
    @FXML
    private TextFlow noCompra;
    @FXML
    private Button BtnSig;
    public void setDatos(Reserva reserva, Cartelera cartelera) {
        regPeliculaNombre.getChildren().add(new Text(cartelera.getIdpelicula().getNombre()));
        String asientos = "";
        double total = 0;
        for (Boleto b : reserva.getBoletos()) {
            asientos += b.getNombreasiento() + " ";
            total += b.getMonto();
        }

        regAsientos.getChildren().add(new Text(asientos));
        MontoPagar.getChildren().add(new Text("$" + total));
        regSala.getChildren().add(new Text(String.valueOf(cartelera.getIdsala().getIdsala())));
        regHorario.getChildren().add(new Text(cartelera.getHora().toString()));
        noCompra.getChildren().add(new Text(String.valueOf(reserva.getIdReserva())));
    }

    @FXML
    public void BtnSigAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Seleccionar película");
    }
}