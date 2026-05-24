package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.models.Reserva;
import com.Cine.models.Boleto;
import com.Cine.models.Cartelera;
import com.Cine.models.Usuario;

import com.Cine.repository.ReservaRepository;
import com.Cine.services.UsuarioService;
import com.Cine.services.ReservaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Use_4Controller {
    @FXML
    private TableView<Reserva> TableUsuarios;
    @FXML private TableColumn<Reserva, String> horario;
    @FXML private TableColumn<Reserva, String> peliNombre;
    @FXML private TableColumn<Reserva, String> hora;
    @FXML private TableColumn<Reserva, String> cantidadboletos;
    @FXML private TableColumn<Reserva, String> asientos;
    @FXML
    private Label nombreUsuario;
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
    private Button BtnTicket;
    @FXML
    private TableColumn<Reserva, String> ColumPelicula;

    @FXML
    private TableColumn<Reserva, String> ColumAsiento;

    @FXML
    private TableColumn<Reserva, String> ColumFecha;
    private final ReservaRepository reservaRepository = new ReservaRepository();
    private ObservableList<Reserva> lista = FXCollections.observableArrayList();
    public static Usuario usuarioLogueado;

    @FXML
    public void initialize() {
        configurarTabla();
    }
    @FXML
    private void configurarTabla() {

        horario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFecha().toString()));

        hora.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdcartelera().getHora()));

        peliNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdcartelera().getIdpelicula().getNombre()));

        cantidadboletos.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getBoletos().size())));

        asientos.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getBoletos()
                                .stream()
                                .map(b -> b.getNombreasiento())
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("")
                )
        );
    }

    @FXML
    private void BtnEditCuentaAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/CreaCuenta_3.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        CreaCuenta_3Controller controller = fxmlLoader.getController();
        controller.setUsuarioEditar(usuarioLogueado);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync -Editar Cuenta");stage.setScene(nextScene);
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
    private void BtnTicketAction(ActionEvent actionEvent) throws IOException {
        Reserva reservaSeleccionada = TableUsuarios.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona una reserva de la tabla para ver su ticket").showAndWait();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Registro_8.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Registro_8Controller controller = fxmlLoader.getController();
        controller.mostrarTicketReserva(reservaSeleccionada);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Ticket");stage.setScene(nextScene);
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Seleccionar Función");
        stage.setScene(nextScene);
    }
    @FXML
    public void setUsuario(Usuario usuario) {

        if (usuario == null) {
            System.out.println("Usuario nulo recibido");
            return;
        }
        this.usuarioLogueado = usuario;
        nombreUsuario.setText(usuario.getNombre());
        cargarReservas();
    }
    @FXML
    private void cargarReservas() {

        if (usuarioLogueado == null) return;

        List<Reserva> reservas =
                reservaRepository.getReservasByUsuario(usuarioLogueado.getIdusuario());

        TableUsuarios.setItems(
                FXCollections.observableArrayList(reservas)
        );
    }
}