package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.SharedData;
import com.Cine.models.Boleto;
import com.Cine.models.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelecPeli_7Controller {

    @FXML private Label NomAvatar;
    @FXML private ComboBox<String> CmbxLetraAsiento;
    @FXML private TextField TxtAsientoDisp;
    @FXML private Button BtnAtras, BtnCancelar, BtnSig;

    private int totalBoletos;
    private int boletosAsignados = 0;
    // Ahora guardamos objetos Boleto temporales
    private List<Boleto> boletosTemporales = new ArrayList<>();

    @FXML
    public void initialize() {
        SharedData data = SharedData.getInstance();
        if (data.getUsuarioLogueado() != null) {
            NomAvatar.setText(data.getUsuarioLogueado().getNombre());
        }
        totalBoletos = data.getCantidadBoletos();
        CmbxLetraAsiento.setItems(generarAsientosDisponibles());
        actualizarContador();
        TxtAsientoDisp.setEditable(false);
    }

    @FXML
    private void actualizarContador() {
        int restantes = totalBoletos - boletosAsignados;
        TxtAsientoDisp.setText("Restantes: " + restantes);
        if (restantes <= 0) BtnSig.setText("Finalizar");
    }

    @FXML
    private ObservableList<String> generarAsientosDisponibles() {
        List<String> listaDisponibles = new ArrayList<>();
        SharedData data = SharedData.getInstance();

        // CORRECCIÓN: peliActual ahora es objeto Pelicula
        com.Cine.models.Pelicula peliActual = data.getPeliculaSeleccionada();
        LocalDate fechaActual = data.getFechaSeleccionada();

        String[] filas = {"A", "B", "C", "D", "E"};
        for (String f : filas) {
            for (int i = 1; i <= 5; i++) {
                String idAsiento = f + i;

                // Checamos si ya se eligió en esta pantalla
                boolean yaElegidoEnEstaSesion = boletosTemporales.stream()
                        .anyMatch(b -> b.getNombreasiento().equals(idAsiento));

                boolean ocupadoEnOtrasVentas = false;

                for (Reserva r : data.getHistorialCompras()) {
                    // CORRECCIÓN: Navegamos Reserva -> Cartelera -> Pelicula
                    if (r.getCartelera() != null && r.getCartelera().getIdpelicula() != null) {
                        boolean mismaPeli = r.getCartelera().getIdpelicula().getNombre().equals(peliActual.getNombre());
                        boolean mismaFecha = r.getFecha().equals(fechaActual);

                        if (mismaPeli && mismaFecha) {
                            // CORRECCIÓN: Buscamos en la lista de boletos de la reserva
                            if (r.getBoletos() != null) {
                                for (Boleto b : r.getBoletos()) {
                                    if (b.getNombreasiento().equals(idAsiento)) {
                                        ocupadoEnOtrasVentas = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (ocupadoEnOtrasVentas) break;
                }

                if (!yaElegidoEnEstaSesion && !ocupadoEnOtrasVentas) {
                    listaDisponibles.add(idAsiento);
                }
            }
        }
        return FXCollections.observableArrayList(listaDisponibles);
    }

    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException {
        String seleccion = CmbxLetraAsiento.getValue();
        if (seleccion == null) {
            mostrarAlerta("Atención", "Por favor, selecciona un asiento.");
            return;
        }

        // Creamos un objeto Boleto y lo añadimos a la lista temporal
        Boleto nuevoBoleto = new Boleto();
        nuevoBoleto.setNombreasiento(seleccion);
        boletosTemporales.add(nuevoBoleto);

        boletosAsignados++;

        if (boletosAsignados < totalBoletos) {
            CmbxLetraAsiento.getSelectionModel().clearSelection();
            CmbxLetraAsiento.setItems(generarAsientosDisponibles());
            actualizarContador();
        } else {
            // FINALIZAR: Creamos la reserva real
            SharedData data = SharedData.getInstance();
            Reserva nuevaReserva = new Reserva();

            nuevaReserva.setFecha(data.getFechaSeleccionada());
            nuevaReserva.setUsuario(data.getUsuarioLogueado());

            // CORRECCIÓN: Usamos CarteleraSeleccionada del SharedData
            nuevaReserva.setCartelera(data.getCarteleraSeleccionada());

            // CORRECCIÓN: Pasamos la lista de boletos que fuimos armando
            nuevaReserva.setBoletos(new ArrayList<>(boletosTemporales));

            // Guardamos en SharedData para el siguiente paso
            data.setBoletosTemporales(boletosTemporales);
            data.agregarAlHistorial(nuevaReserva);

            navegar(actionEvent, "views/Registro_8.fxml", "CineSync - Registro");
        }
    }

    @FXML
    public void BtnCancelarAction(ActionEvent actionEvent) {
        boletosAsignados = 0;
        boletosTemporales.clear();
        CmbxLetraAsiento.getSelectionModel().clearSelection();
        CmbxLetraAsiento.setItems(generarAsientosDisponibles());
        actualizarContador();
        BtnSig.setText("Siguiente");
    }

    @FXML
    public void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        navegar(actionEvent, "views/Pinci_5.fxml", "CineSync - Selección");
    }

    private void navegar(ActionEvent event, String path, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(path));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle(titulo);
        stage.setScene(scene);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML public void CmbxLetraAsientoAction(ActionEvent actionEvent) {}
}