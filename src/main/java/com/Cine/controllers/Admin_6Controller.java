package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.models.Sala;
import com.Cine.repository.SalaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.Cine.models.Pelicula;
import com.Cine.services.PeliculaService;
import com.Cine.models.Idioma;
import com.Cine.models.ClasificacionRTC;
import com.Cine.repository.IdiomaRepository;
import com.Cine.repository.ClasificacionRTCRepository;


import java.io.IOException;

public class Admin_6Controller {

    @FXML
    private TextField TextSinopsis;
    @FXML
    private TextField TextCupos;
    @FXML
    private ImageView SubirImg;
    @FXML
    private ComboBox<Idioma> CmbxIdioma;
    @FXML
    private ComboBox<ClasificacionRTC> CmbxClasificacion;
    @FXML
    private TableColumn ColumUsuario;
    @FXML
    private TableColumn ColumNombre;
    @FXML
    private TableColumn ColumCorreo;
    @FXML
    private TextField TextNomEliPeli;
    @FXML
    private TextField TextNomAgPeli;
    @FXML
    private Button BtnAgregarPeli;

    @FXML
    private Button BtnElimPeli;

    @FXML
    private Button BtnSubirImg;

    @FXML
    private ComboBox<Sala> CmbxSala;

    @FXML
    private DatePicker PickerDay;

    @FXML
    private Button BtnVer;

    @FXML
    private Button BtnEliminar;

    @FXML
    private Button BtnAtras;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnSig;
    @FXML
    private ComboBox<Pelicula> CmbxPeli;
    private final PeliculaService peliculaService =
            new PeliculaService();

    private final IdiomaRepository idiomaRepository =
            new IdiomaRepository();

    private final ClasificacionRTCRepository clasificacionRepository =
            new ClasificacionRTCRepository();
    private final SalaRepository salaRepository =
            new SalaRepository();

    @FXML
    public void initialize() {
        CmbxSala.getItems().addAll(salaRepository.getAllSalas());
        CmbxIdioma.getItems().addAll(idiomaRepository.getAllIdiomas());

        CmbxClasificacion.getItems().addAll(clasificacionRepository.getAllClasificaciones());
        CmbxPeli.getItems().addAll(peliculaService.obtenerPeliculas());

        CmbxPeli.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });

        CmbxPeli.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });
    }

    @FXML
    public void BtnAgregaPeliAction(ActionEvent actionEvent) {

        try {
            String nombre = TextNomAgPeli.getText();
            Idioma idioma = CmbxIdioma.getValue();
            String sinopsis = TextSinopsis.getText();
            ClasificacionRTC clasificacion = CmbxClasificacion.getValue();

            if(nombre.isEmpty() || idioma == null || clasificacion == null){
                System.out.println("Completa todos los campos");
                return;
            }
            Pelicula pelicula = new Pelicula();
            pelicula.setNombre(nombre);
            pelicula.setTiempo(120);
            pelicula.setSinopsis(sinopsis);
            pelicula.setImagenURL("default.jpg");
            pelicula.setIdIdioma(idioma);
            pelicula.setIdClasificacionRTC(clasificacion);
            peliculaService.agregarPelicula(pelicula);
            System.out.println("Película guardada");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

@FXML
    public void PickerDayAction(ActionEvent actionEvent) {
    }
@FXML
    public void BtnVerAction(ActionEvent actionEvent) {
    }
    @FXML
    public void BtnElimPeliAction(ActionEvent actionEvent) {

        try {
            Pelicula peliculaSeleccionada = CmbxPeli.getValue();

            if (peliculaSeleccionada == null) {
                System.out.println("Selecciona una película");
                return;
            }

            peliculaService.eliminarPelicula(peliculaSeleccionada);

            System.out.println("Película eliminada correctamente");
            CmbxPeli.getItems().clear();
            CmbxPeli.getItems().addAll(peliculaService.obtenerPeliculas()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
@FXML
    public void BtnCancelarAction(ActionEvent actionEvent) {
    TextNomAgPeli.clear();
    TextSinopsis.clear();
    TextCupos.clear();
    CmbxSala.getSelectionModel().clearSelection();
    CmbxIdioma.getSelectionModel().clearSelection();
    CmbxClasificacion.getSelectionModel().clearSelection();
    }
@FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException {
    Scene scene = ((Button) actionEvent.getSource()).getScene();
    Stage stage = (Stage) scene.getWindow();

    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/admin_7.fxml"));
    Scene nextScene = new Scene(fxmlLoader.load());

    stage.setTitle("CineSync - Funciones");
    stage.setScene(nextScene);
    }
@FXML
    public void OnActionBtnAtras(ActionEvent actionEvent)throws IOException {
    Scene scene = ((Button) actionEvent.getSource()).getScene();
    Stage stage = (Stage) scene.getWindow();

    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
    Scene nextScene = new Scene(fxmlLoader.load());

    stage.setTitle("CineSync - Iniciar Sesion");
    stage.setScene(nextScene);
    }
    @FXML
    public void CmbxClasificacionAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxIdiomaAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxSalaAction(ActionEvent actionEvent) {

        Sala salaSeleccionada = CmbxSala.getValue();

        if(salaSeleccionada != null){

            TextCupos.setText(String.valueOf(salaSeleccionada.getCapacidad()));
        }
    }
    @FXML
    public void BtnEliminarAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxElimPeliAction(ActionEvent actionEvent) {
    }
}
