package com.Cine.controllers;
import com.Cine.MainApplication;
import com.Cine.models.Boleto;
import com.Cine.models.Cartelera;
import com.Cine.models.Pelicula;
import com.Cine.models.Reserva;
import com.Cine.models.Usuario;
import com.Cine.services.ReservaService;
import com.Cine.services.PeliculaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.Callback;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pinci_5Controller {

    @FXML private ComboBox<Pelicula> CmbxPelicula;
    @FXML private DatePicker PickerDay;
    @FXML private Button BtnAtras, BtnCancelar, BtnSig;
    @FXML
    private ComboBox<String> CmbxHorario;
    private final PeliculaService peliculaService = new PeliculaService();
    private final ReservaService reservaService = new ReservaService();
    private Usuario usuarioLogueado;
    @FXML
    public void initialize() {
        PickerDay.setOnAction(this::PickerDayAction);
        CmbxPelicula.getItems().addAll(peliculaService.obtenerPeliculas());
        CmbxPelicula.setConverter(new StringConverter<Pelicula>() {
            @Override
            public String toString(Pelicula pelicula) {
                if(pelicula == null){
                    return "";
                }
                return pelicula.getNombre();
            }
            @Override
            public Pelicula fromString(String string) {
                return null;
            }
        });
        CmbxPelicula.setOnAction(this::CmbxPeliculaAction);
    }
    @FXML
    private void BtnSigAction(ActionEvent event) throws IOException {

        Pelicula peli = CmbxPelicula.getValue();
        LocalDate fecha = PickerDay.getValue();
        String horario = CmbxHorario.getValue();

        if (peli == null || fecha == null || horario == null) {
            new Alert(Alert.AlertType.WARNING, "Completa todos los datos").showAndWait();
            return;
        }
        Cartelera cartelera = null;
        for (Cartelera funcion : peli.getFunciones()) {
            if (funcion.getFecha().equals(fecha) && funcion.getHora().equals(horario)) {
                cartelera = funcion;
                break;
            }
        }

        if (cartelera == null) {
            new Alert(Alert.AlertType.ERROR, "No se encontró la función").showAndWait();
            return;
        }
        SelecPeli_7Controller.usuarioLogueado = Use_4Controller.usuarioLogueado;

        SelecPeli_7Controller.carteleraActual = cartelera;
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/SelecPeli_7.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setTitle("Seleccionar Asientos");
        stage.setScene(scene);
    }

    @FXML
    private void BtnCancelarAction() {
        CmbxPelicula.getSelectionModel().clearSelection();
        PickerDay.setValue(null);
    }

    @FXML
    private void BtnAtrasAction(ActionEvent event) throws IOException {
        Scene scene = ((Button) event.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        stage.setTitle("Cine-Sync Perfil");
        stage.setScene(mainScene);
    }

    @FXML
    private void CmbxPeliculaAction(ActionEvent event) {
        Pelicula pelicula = CmbxPelicula.getValue();
        if(pelicula == null){
            return;
        }
        Set<LocalDate> fechas = new HashSet<>();
        for(Cartelera funcion : pelicula.getFunciones()){
            fechas.add(funcion.getFecha());
        }
        PickerDay.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate fecha, boolean empty){
                super.updateItem(fecha, empty);
                if(empty){
                    return;
                }
                if(!fechas.contains(fecha)){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }

    @FXML
    private void PickerDayAction(ActionEvent event) {

        Pelicula peli = CmbxPelicula.getValue();
        LocalDate fecha = PickerDay.getValue();
        if(peli == null || fecha == null){
            return;
        }
        CmbxHorario.getItems().clear();
        for(Cartelera funcion : peli.getFunciones()){
            if(funcion.getFecha().equals(fecha)){
                CmbxHorario.getItems().add(funcion.getHora());
            }
        }
    }
}