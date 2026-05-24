package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Asiento;
import com.Cine.models.Boleto;
import com.Cine.models.Cartelera;
import com.Cine.models.Reserva;
import com.Cine.models.Usuario;
import com.Cine.services.AsientoService;
import com.Cine.services.ReservaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class SelecPeli_7Controller {
    private Usuario usuarioLogueado;
    private CarteleraDTO cartelera;
    private final ReservaService reservaService = new ReservaService();
    private final AsientoService asientoService = new AsientoService();
    @FXML private TextField TxtAsientoDisp;
    @FXML private Button buttonA1, buttonA2, buttonA3, buttonA4, buttonA5;
    @FXML private Button buttonB1, buttonB2, buttonB3, buttonB4, buttonB5;
    @FXML private Button buttonC1, buttonC2, buttonC3, buttonC4, buttonC5;
    @FXML private Button buttonD1, buttonD2, buttonD3, buttonD4, buttonD5;
    @FXML private Button buttonE1, buttonE2, buttonE3, buttonE4, buttonE5;

    private final Map<String, Button> mapa = new HashMap<>();

    private final Set<String> seleccion = new HashSet<>();
@FXML
public void cargarDatos() {
    bloquearOcupados();
}
    @FXML
    public void initialize() {
        mapearBotones();
        actualizarTexto();
    }

    private void mapearBotones() {
        mapa.put("A1", buttonA1);
        mapa.put("A2", buttonA2);
        mapa.put("A3", buttonA3);
        mapa.put("A4", buttonA4);
        mapa.put("A5", buttonA5);
        mapa.put("B1", buttonB1);
        mapa.put("B2", buttonB2);
        mapa.put("B3", buttonB3);
        mapa.put("B4", buttonB4);
        mapa.put("B5", buttonB5);
        mapa.put("C1", buttonC1);
        mapa.put("C2", buttonC2);
        mapa.put("C3", buttonC3);
        mapa.put("C4", buttonC4);
        mapa.put("C5", buttonC5);
        mapa.put("D1", buttonD1);
        mapa.put("D2", buttonD2);
        mapa.put("D3", buttonD3);
        mapa.put("D4", buttonD4);
        mapa.put("D5", buttonD5);
        mapa.put("E1", buttonE1);
        mapa.put("E2", buttonE2);
        mapa.put("E3", buttonE3);
        mapa.put("E4", buttonE4);
        mapa.put("E5", buttonE5);
    }

    private void bloquearOcupados() {
        List<Reserva> reservas = reservaService.obtenerReservasPorFuncion(cartelera.idCartelera()
        );

        for (Reserva r : reservas) {

            for (Boleto b : r.getBoletos()) {
                String asiento = b.getNombreasiento();
                Button btn = mapa.get(asiento);
                if (btn != null) {
                    btn.setDisable(true);
                    btn.setStyle("-fx-background-color:red; -fx-text-fill:white;");
                }
            }
        }
    }

    @FXML
    public void seleccionarAsiento(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String asiento = btn.getText();
        if (seleccion.contains(asiento)) {
            seleccion.remove(asiento);
            btn.setStyle("");
        } else {
            seleccion.add(asiento);
            btn.setStyle("-fx-background-color:green; -fx-text-fill:white;");
        }
        actualizarTexto();
    }

    private void actualizarTexto() {
        TxtAsientoDisp.setText("Seleccionados: " + seleccion.size());
    }

    @FXML
    public void BtnSigAction(ActionEvent event) throws IOException {

        if (seleccion.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Selecciona al menos un asiento").showAndWait();
            return;
        }
        Reserva reserva = reservaService.procesarCompra(usuarioLogueado, cartelera, new ArrayList<>(seleccion));
        new Alert(Alert.AlertType.INFORMATION, "Reserva creada: " + reserva.getIdReserva()).showAndWait();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/Registro_8.fxml"));
        Scene scene = new Scene(loader.load());
        Registro_8Controller controller = loader.getController();

        controller.setDatos(reserva, cartelera);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Cine-Sync Confirmación");
    }

    @FXML
    public void BtnCancelarAction() {
        seleccion.clear();

        for (Button btn : mapa.values()) {
            if (!btn.isDisabled()) {
                btn.setStyle("");
            }
        }
        actualizarTexto();
    }

    @FXML
    public void BtnAtrasAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("CineSync -Seleccionar Funcion");
    }
    public void setCarteleraDTO(CarteleraDTO cartelera) {
        this.cartelera = cartelera;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogueado = usuario;
    }
}