package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.models.Reserva;
import com.Cine.models.Usuario;

import com.Cine.services.UsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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

    private ObservableList<Reserva> lista =
            FXCollections.observableArrayList();

    private Usuario usuarioLogueado;

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void BtnEditCuentaAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/CreaCuenta_3.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        CreaCuenta_3Controller controller = fxmlLoader.getController();
        controller.setUsuarioEditar(usuarioLogueado);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Editar Cuenta");stage.setScene(nextScene);
    }

    @FXML
    private void BtnElimCuentaAction(ActionEvent actionEvent) throws IOException {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar cuenta");
        alerta.setHeaderText("¿Seguro que deseas eliminar tu cuenta?");
        alerta.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            UsuarioService usuarioService = new UsuarioService();
            usuarioService.eliminarUsuario(usuarioLogueado);
            Alert exito = new Alert(Alert.AlertType.INFORMATION);

            exito.setTitle("Cuenta eliminada");

            exito.setHeaderText(null);

            exito.setContentText("Tu cuenta fue eliminada correctamente.");
            exito.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("CineSync - Iniciar Sesión");
            stage.setScene(nextScene);
        }
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Iniciar Sesion");stage.setScene(nextScene);
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