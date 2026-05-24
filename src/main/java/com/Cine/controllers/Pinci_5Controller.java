package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Pelicula;
import com.Cine.models.Usuario;
import com.Cine.models.Cartelera;
import com.Cine.services.PeliculaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
public class Pinci_5Controller {
    @FXML
    private FlowPane FlowCartelera;
    @FXML
    private Label LblPeliSelec;
    @FXML
    private TableView<Cartelera> TblFunciones;
    @FXML
    private TableColumn<Cartelera, String> ColFecha;
    @FXML
    private TableColumn<Cartelera, String> ColHora;
    @FXML
    private TableColumn<Cartelera, String> ColSala;
    @FXML
    private TableColumn<Cartelera, String> ColIdioma;
    @FXML
    private Button BtnAtras, BtnCancelar, BtnSig;
    public static Usuario usuarioLogueado;
    private final PeliculaService peliculaService = new PeliculaService();

    private Pelicula peliculaSeleccionada;
    private Cartelera funcionSeleccionada;

    @FXML
    public void initialize() {
        configurarTabla();
        TblFunciones.setPlaceholder(new Label("Selecciona una película"));
        cargarCartelera();
    }
    // =========================
    // CONFIGURAR TABLA
    // =========================
    @FXML
    private void configurarTabla() {

        ColFecha.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getFecha().toString())
        );

        ColHora.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getHora())
        );

        ColSala.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getIdsala().getNombreTipoSala())
        );

        ColIdioma.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getIdpelicula().getIdIdioma().getNombreIdioma())
        );

        TblFunciones.setOnMouseClicked(e -> {
            funcionSeleccionada = TblFunciones.getSelectionModel().getSelectedItem();
        });
    }

    @FXML
    private void cargarCartelera() {

        List<Pelicula> peliculas = peliculaService.obtenerPeliculasConCartelera();

        FlowCartelera.getChildren().clear();

        for (Pelicula p : peliculas) {

            VBox card = new VBox();
            card.setSpacing(5);
            card.getStyleClass().add("card-pelicula");

            ImageView imgView = new ImageView(
                    new Image(new ByteArrayInputStream(p.getImagen()))
            );

            imgView.setFitWidth(120);
            imgView.setFitHeight(160);
            imgView.setPreserveRatio(true);

            Label nombre = new Label(p.getNombre());

            card.getChildren().addAll(imgView, nombre);

            card.setOnMouseClicked(e -> {
                setPelicula(p);
                cargarFunciones(p);
            });

            FlowCartelera.getChildren().add(card);
        }
    }
    // =========================
    // CUANDO SELECCIONAS PELÍCULA (desde cards)
    // =========================
    @FXML
    public void setPelicula(Pelicula pelicula) {
        this.peliculaSeleccionada = pelicula;

        LblPeliSelec.setText(pelicula.getNombre());
    }

    // =========================
    // CARGAR FUNCIONES (DTO)
    // =========================
    @FXML
    private void cargarFunciones(Pelicula pelicula) {

        TblFunciones.setItems(
                FXCollections.observableArrayList(pelicula.getFunciones())
        );
    }

    // =========================
    // SIGUIENTE
    // =========================
    @FXML
    private void BtnSigAction(javafx.event.ActionEvent event) throws IOException {

        if (funcionSeleccionada == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Selecciona una función").showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                MainApplication.class.getResource("views/SelecPeli_7.fxml")
        );

        Scene scene = new Scene(loader.load());

        SelecPeli_7Controller controller = loader.getController();
        CarteleraDTO dto = new CarteleraDTO(
                funcionSeleccionada.getIdCartelera(),
                funcionSeleccionada.getFecha(),
                funcionSeleccionada.getHora(),
                funcionSeleccionada.getIdpelicula().getIdpelicula(),
                funcionSeleccionada.getIdsala().getIdsala()
        );
        controller.setCarteleraDTO(dto);
        controller.setUsuario(Use_4Controller.usuarioLogueado);
        controller.cargarDatos();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    // =========================
    // CANCELAR
    // =========================
    @FXML
    private void BtnCancelarAction() {
        funcionSeleccionada = null;
        TblFunciones.getSelectionModel().clearSelection();
    }

    // =========================
    // ATRÁS
    // =========================
    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Use_4Controller controller = fxmlLoader.getController();
        controller.setUsuario(Use_4Controller.usuarioLogueado);
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync -Cuenta");
        stage.setScene(nextScene);
    }
}