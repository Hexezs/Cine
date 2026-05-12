package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.SharedData;
import com.Cine.models.Reserva; // IMPORTANTE: Usamos el modelo de la carpeta models
import com.Cine.models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Use_4Controller {

    @FXML private Button BtnEditCuenta;
    @FXML private Button BtnElimCuenta;
    @FXML private Button BtnAtras;
    @FXML private Button BtnCancelar;
    @FXML private Button BtnSig;

    // Cambiamos el tipo de TableView para que use el modelo real
    @FXML private TableView<Reserva> tableView;

    @FXML private TableColumn<Reserva, String> ColumPelicula;
    @FXML private TableColumn<Reserva, String> ColumAsiento;
    @FXML private TableColumn<Reserva, String> ColumFecha;

    private ObservableList<Reserva> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        SharedData data = SharedData.getInstance();
        ColumPelicula.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCartelera() != null &&
                    cellData.getValue().getCartelera().getIdpelicula() != null) {
                return new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCartelera().getIdpelicula().getNombre()
                );
            }
            return new javafx.beans.property.SimpleStringProperty("N/A");
        });
        ColumAsiento.setCellValueFactory(cellData -> {
            java.util.List<com.Cine.models.Boleto> boletos = cellData.getValue().getBoletos();

            if (boletos != null && !boletos.isEmpty()) {
                String textoAsientos = boletos.stream()
                        .map(b -> b.getNombreasiento())
                        .filter(n -> n != null)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("N/A");
                return new javafx.beans.property.SimpleStringProperty(textoAsientos);
            }
            return new javafx.beans.property.SimpleStringProperty("Sin asientos");
        });
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        lista.clear();
        if (data.getUsuarioLogueado() != null) {
            String nombreActual = data.getUsuarioLogueado().getNombre();
            for (com.Cine.models.Reserva r : data.getHistorialCompras()) {
                if (r.getUsuario() != null && r.getUsuario().getNombre().equals(nombreActual)) {
                    lista.add(r);
                }
            }
        }
        tableView.setItems(lista);
    }

    @FXML
    private void BtnEditCuentaAction(ActionEvent actionEvent) throws IOException {
        SharedData.getInstance().setModoEdicion(true);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/CreaCuenta_3.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Editar Cuenta");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnElimCuentaAction(ActionEvent actionEvent) throws IOException {
        SharedData data = SharedData.getInstance();
        Usuario usuarioActual = data.getUsuarioLogueado();

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación de Eliminación");
        alerta.setHeaderText("¿Eliminar cuenta y liberar asientos?");
        alerta.setContentText("Tus reservas para las fechas seleccionadas se cancelarán.");

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            data.getHistorialCompras().removeIf(reserva ->
                    reserva.getUsuario() != null &&
                            reserva.getUsuario().getCorreo().equals(usuarioActual.getCorreo())
            );
            data.getListaUsuariosGlobal().removeIf(u ->
                    u.getCorreo().equals(usuarioActual.getCorreo())
            );
            data.limpiarSesion();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("CineSync - Iniciar Sesion");
            stage.setScene(nextScene);
        }
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Iniciar Sesion");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnCancelarAction() {
        lista.clear();
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Comprar Boletos");
        stage.setScene(nextScene);
    }
}