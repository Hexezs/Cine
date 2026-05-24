package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.dto.CarteleraDTO;
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
    @FXML
    public void setDatos(Reserva reserva, CarteleraDTO cartelera) {
        regPeliculaNombre.getChildren().clear();
        regAsientos.getChildren().clear();
        MontoPagar.getChildren().clear();
        regSala.getChildren().clear();
        regHorario.getChildren().clear();
        noCompra.getChildren().clear();
        regPeliculaNombre.getChildren().add(new Text("ID Película: " + cartelera.idpelicula()));
        StringBuilder asientos = new StringBuilder();
        double total = 0;
        for (Boleto b : reserva.getBoletos()) {
            asientos.append(b.getNombreasiento()).append(" ");
            total += b.getMonto();
        }
        regAsientos.getChildren().add(new Text(asientos.toString()));
        MontoPagar.getChildren().add(new Text("$" + total));
        regSala.getChildren().add(new Text("Sala: " + cartelera.idsala()));
        regHorario.getChildren().add(new Text(cartelera.hora()));
        noCompra.getChildren().add(new Text(String.valueOf(reserva.getIdReserva())));
    }

    @FXML
    public void mostrarTicketReserva(Reserva reserva) {
        regPeliculaNombre.getChildren().clear();
        regAsientos.getChildren().clear();
        MontoPagar.getChildren().clear();
        regSala.getChildren().clear();
        regHorario.getChildren().clear();
        noCompra.getChildren().clear();
        Cartelera cartelera = reserva.getIdcartelera();
        regPeliculaNombre.getChildren().add(new Text(cartelera.getIdpelicula().getNombre()));
        StringBuilder asientos = new StringBuilder();
        double total = 0;
        for (Boleto b : reserva.getBoletos()) {
            asientos.append(b.getNombreasiento()).append(" ");
            total += b.getMonto();
        }
        regAsientos.getChildren().add(new Text(asientos.toString()));
        MontoPagar.getChildren().add(new Text("$" + total));
        regSala.getChildren().add(new Text("Sala: " + cartelera.getIdsala().getIdsala()));
        regHorario.getChildren().add(new Text(cartelera.getHora()));
        noCompra.getChildren().add(new Text(String.valueOf(reserva.getIdReserva())));
    }

    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Use_4Controller controller = fxmlLoader.getController();
        controller.setUsuario(Use_4Controller.usuarioLogueado);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync -Cuenta");
        stage.setScene(nextScene);
    }
}