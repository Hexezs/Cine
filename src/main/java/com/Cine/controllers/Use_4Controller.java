package com.Cine.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
    private void BtnEditCuentaAction() {
        System.out.println("Editar cuenta");
    }

    @FXML
    private void BtnElimCuentaAction() {
        System.out.println("Eliminar cuenta");
    }

    @FXML
    private void BtnAtrasAction() {
        System.out.println("Atrás");
    }

    @FXML
    private void BtnCancelarAction() {
        System.out.println("Cancelar");

        tableView.getItems().clear();
    }

    @FXML
    private void BtnSigAction() {
        System.out.println("Siguiente");
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