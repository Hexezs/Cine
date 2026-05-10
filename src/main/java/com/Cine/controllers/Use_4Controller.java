package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.SharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

//import com.gluonhq.charm.glisten.control.Avatar;

public class Use_4Controller {

    @FXML
    private Button BtnEditCuenta;

    @FXML
    private Button BtnElimCuenta;

    @FXML
    private Button BtnAtras;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnSig;

    @FXML
    private TableView<Reserva> tableView;

    @FXML
    private TableColumn<Reserva, String> ColumPelicula;

    @FXML
    private TableColumn<Reserva, String> ColumAsiento;

    @FXML
    private TableColumn<Reserva, String> ColumFecha;

//    @FXML
//    private Avatar Avatar;
//
//    @FXML
//    private TextFlow NomAvatar;

    private ObservableList<Reserva> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("Pantalla usuario cargada");

        // Configurar columnas
        ColumPelicula.setCellValueFactory(new PropertyValueFactory<>("pelicula"));
        ColumAsiento.setCellValueFactory(new PropertyValueFactory<>("asiento"));
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Datos de prueba
        lista.add(new Reserva("Avengers", "A1", "2026-04-20"));
        lista.add(new Reserva("Batman", "B5", "2026-04-22"));

        tableView.setItems(lista);

        // Nombre en avatar
//        NomAvatar.getChildren().add(new Text("Usuario Demo"));
    }

    @FXML
    private void BtnEditCuentaAction(ActionEvent actionEvent)throws IOException {
        SharedData.getInstance().setModoEdicion(true);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/CreaCuenta_3.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        SharedData.getInstance().setModoEdicion(true);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Editar Cuenta");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnElimCuentaAction(ActionEvent actionEvent)throws IOException {
        System.out.println("Eliminar cuenta");
        SharedData data = SharedData.getInstance();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación de Eliminación");
        alerta.setHeaderText("¿Estás seguro de que deseas eliminar tu cuenta?");
        alerta.setContentText("Esta acción limpiará tu sesión actual y regresará al inicio.");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            System.out.println("Usuario confirmó eliminación.");
            data.limpiarSesion();
            Scene scene = ((Button) actionEvent.getSource()).getScene();
            Stage stage = (Stage) scene.getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            stage.setTitle("CineSync - Iniciar Sesion");
            stage.setScene(nextScene);
        } else {
            System.out.println("Eliminación cancelada por el usuario.");
        }
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Atrás");
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Iniciar Sesion");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnCancelarAction() {
        System.out.println("Cancelar");

        tableView.getItems().clear();
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Siguiente");
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());
        stage.setTitle("CineSync - Comprar Boeltos");
        stage.setScene(nextScene);
    }

    // Clase interna para la tabla (temporal)
    public static class Reserva {
        private String pelicula;
        private String asiento;
        private String fecha;

        public Reserva(String pelicula, String asiento, String fecha) {
            this.pelicula = pelicula;
            this.asiento = asiento;
            this.fecha = fecha;
        }

        public String getPelicula() {
            return pelicula;
        }

        public String getAsiento() {
            return asiento;
        }

        public String getFecha() {
            return fecha;
        }

    }

}