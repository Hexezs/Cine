package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.SharedData;
import com.Cine.models.Pelicula; // Importante
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Pinci_5Controller {
    @FXML private TextField TextCantidadBoletos;
    @FXML private ComboBox<Pelicula> CmbxPelicula;
    @FXML private DatePicker PickerDay;
    @FXML private Button BtnAtras, BtnCancelar, BtnSig;

    @FXML
    public void initialize() {
        SharedData data = SharedData.getInstance();
        CmbxPelicula.setConverter(new StringConverter<Pelicula>() {
            @Override
            public String toString(Pelicula peli) {
                return (peli == null) ? "" : peli.getNombre();
            }
            @Override
            public Pelicula fromString(String string) { return null; }
        });
        CmbxPelicula.getItems().clear();
        CmbxPelicula.getItems().addAll(data.getListaPeliculasGlobal());
        if (data.getPeliculaSeleccionada() != null) {
            CmbxPelicula.setValue(data.getPeliculaSeleccionada());
        }

        if (data.getPeliculaSeleccionada() != null) {
            CmbxPelicula.setValue(data.getPeliculaSeleccionada());
        }
        if (data.getFechaSeleccionada() != null) {
            PickerDay.setValue(data.getFechaSeleccionada());
        }
        if (data.getCantidadBoletos() > 0) {
            TextCantidadBoletos.setText(String.valueOf(data.getCantidadBoletos()));
        }
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        Pelicula peli = CmbxPelicula.getValue();
        LocalDate fecha = PickerDay.getValue();
        String cantStr = TextCantidadBoletos.getText();

        if (peli == null || fecha == null || cantStr.isEmpty()) {
            System.out.println("Faltan datos");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantStr);

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("¿Desea continuar?");
            alerta.setContentText("Película: " + peli.getNombre() + "\nFecha: " + fecha + "\nBoletos: " + cantidad);

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                SharedData data = SharedData.getInstance();
                data.setPeliculaSeleccionada(peli);
                data.setFechaSeleccionada(fecha);
                data.setCantidadBoletos(cantidad);

                navegar(actionEvent, "views/SelecPeli_7.fxml", "CineSync - Seleccionar Asiento");
            }
        } catch (NumberFormatException e) {
            System.out.println("La cantidad debe ser un número");
        }
    }

    private void navegar(ActionEvent event, String path, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(path));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle(titulo);
        stage.setScene(scene);
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        navegar(actionEvent, "views/Use_4.fxml", "CineSync - Mi Perfil");
    }

    @FXML
    private void BtnCancelarAction() {
        TextCantidadBoletos.clear();
        CmbxPelicula.getSelectionModel().clearSelection();
        PickerDay.setValue(null);
    }

    @FXML private void CmbxPeliculaAction() {}
    @FXML private void PickerDayAction() {}
}