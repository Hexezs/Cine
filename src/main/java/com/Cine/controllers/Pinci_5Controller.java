package com.Cine.controllers;

import com.Cine.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Picker;
import org.hibernate.tool.schema.Action;

import java.io.IOException;

public class Pinci_5Controller {
    @FXML
    private TextField TextCantidadBoletos;
    @FXML
    private Button BtnAtras;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnSig;

    @FXML
    private ComboBox<String> CmbxPelicula;

    @FXML
    private DatePicker PickerDay;

//    @FXML
//    private Avater Avatar;

    @FXML
    public void initialize(){
        CmbxPelicula.getItems().addAll("GATO", "GATO2", "GATO 3");

    }
    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException{
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Rol de usuario");
        stage.setScene(nextScene);
    }

    @FXML
    private  void BtnCancelarAction(){
    TextCantidadBoletos.clear();
    CmbxPelicula.getSelectionModel().clearSelection();
    PickerDay.setValue(null);
    }
    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/selecPeli_7.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Seleccionar Asiento");
        stage.setScene(nextScene);
    }

    @FXML
    private  void CmbxPeliculaAction(){

    }

    @FXML
    private void PickerDayAction(){

    }
    }

