package com.Cine.controllers;

import com.Cine.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Picker;

import java.io.IOException;

public class Admin_6Controller {
    @FXML
    private Button BtnAgregarPeli;

    @FXML
    private Button BtnElimPeli;

    @FXML
    private Button BtnSubirImg;

    @FXML
    private ComboBox<String> CmbxSala;

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
    public void initialize() {
    }

@FXML
    public void BtnAgregaPeliAction(ActionEvent actionEvent) {
    }
@FXML
    public void BtnElimPeliAction(ActionEvent actionEvent) {
    }
@FXML
    public void CmbxSalaAction(ActionEvent actionEvent) {
    }
@FXML
    public void PickerDayAction(ActionEvent actionEvent) {
    }
@FXML
    public void BtnVerAction(ActionEvent actionEvent) {
    }
@FXML
    public void BtnEliminarAction(ActionEvent actionEvent) {
    }
@FXML
    public void BtnCancelarAction(ActionEvent actionEvent) {
    }
@FXML
    public void BtnSigAction(ActionEvent actionEvent) {
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
}
