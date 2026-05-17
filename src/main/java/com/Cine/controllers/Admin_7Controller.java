package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.models.Cartelera;
import com.Cine.models.Pelicula;
import com.Cine.models.Sala;
import com.Cine.models.Usuario;
import com.Cine.repository.CarteleraRepository;
import com.Cine.repository.PeliculaRepository;
import com.Cine.repository.SalaRepository;
import com.Cine.repository.UsuarioRepository;

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
import java.time.LocalDate;
import java.util.List;

public class Admin_7Controller {

    @FXML
    private ComboBox<Pelicula> CmbxPelicula;

    @FXML
    private ComboBox<Sala> CmbxSala;

    @FXML
    private DatePicker PickerFecha;

    @FXML
    private ComboBox<String> CmbxHora;

    @FXML
    private TableView<Cartelera> TableFunciones;

    @FXML
    private TableColumn<Cartelera, Integer> ColumIdFuncion;

    @FXML
    private TableColumn<Cartelera, String> ColumPelicula;

    @FXML
    private TableColumn<Cartelera, LocalDate> ColumFecha;

    @FXML
    private TableColumn<Cartelera, String> ColumHora;

    @FXML
    private TableColumn<Cartelera, String> ColumSala;

    @FXML
    private TableView<Usuario> TableUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> ColumIdUsuario;

    @FXML
    private TableColumn<Usuario, String> ColumNombre;

    @FXML
    private TableColumn<Usuario, String> ColumCorreo;

    @FXML
    private Button BtnAgregarFuncion;

    @FXML
    private Button BtnEliminarFuncion;

    @FXML
    private Button BtnActualizar;

    @FXML
    private Button BtnAtras;

    private final PeliculaRepository peliculaRepository =
            new PeliculaRepository();

    private final SalaRepository salaRepository =
            new SalaRepository();

    private final CarteleraRepository carteleraRepository =
            new CarteleraRepository();

    private final UsuarioRepository usuarioRepository =
            new UsuarioRepository();

    private ObservableList<Cartelera> listaFunciones =
            FXCollections.observableArrayList();

    private ObservableList<Usuario> listaUsuarios =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarPeliculas();
        cargarSalas();
        cargarHoras();
        configurarTablaFunciones();
        configurarTablaUsuarios();
        cargarFunciones();
        TableFunciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue != null){
                        cargarUsuariosFuncion(newValue);
                    }
                });
    }

    private void cargarPeliculas() {
        CmbxPelicula.getItems().addAll(peliculaRepository.getAllPeliculas());
        CmbxPelicula.setCellFactory(param -> new ListCell<>() {
                    @Override
                    protected void updateItem(Pelicula item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty || item == null){
                            setText(null);
                        }else{
                            setText(item.getNombre());
                        }
                    }
                });

        CmbxPelicula.setButtonCell(new ListCell<>() {
                    @Override
                    protected void updateItem(Pelicula item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty || item == null){
                            setText(null);
                        }else{
                            setText(item.getNombre());
                        }
                    }
                });
    }

    private void cargarSalas() {
        CmbxSala.getItems().addAll(salaRepository.getAllSalas());
        CmbxSala.setCellFactory(param -> new ListCell<>() {
                    @Override
                    protected void updateItem(Sala item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty || item == null){
                            setText(null);
                        }else{
                            setText("Sala " + item.getIdsala() + " - " + item.getNombreTipoSala());
                        }
                    }
                });

        CmbxSala.setButtonCell(new ListCell<>() {

                    @Override
                    protected void updateItem(Sala item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty || item == null){
                            setText(null);
                        }else{
                            setText("Sala " + item.getIdsala() + " - " + item.getNombreTipoSala());
                        }
                    }
                });
    }

    private void cargarHoras() {

        CmbxHora.getItems().addAll(

                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00",
                "20:00",
                "22:00"
        );
    }

    private void configurarTablaFunciones() {

        ColumIdFuncion.setCellValueFactory(new PropertyValueFactory<>("idCartelera"));
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        ColumHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        ColumPelicula.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIdpelicula().getNombre()));

        ColumSala.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("Sala " + cellData.getValue().getIdsala().getIdsala()));

        TableFunciones.setItems(listaFunciones);
    }

    private void configurarTablaUsuarios() {
        ColumIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idusuario"));
        ColumNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        TableUsuarios.setItems(listaUsuarios);
    }

    private void cargarFunciones() {
        listaFunciones.clear();
        listaFunciones.addAll(carteleraRepository.getAllCartelera());
    }

    private void cargarUsuariosFuncion(Cartelera funcion) {
        listaUsuarios.clear();
        List<Usuario> usuarios = usuarioRepository.getUsuariosByFuncion(funcion.getIdCartelera());

        listaUsuarios.addAll(usuarios);
    }

    @FXML
    public void BtnAgregarFuncionAction(ActionEvent actionEvent) {
        try {
            Pelicula pelicula = CmbxPelicula.getValue();
            Sala sala = CmbxSala.getValue();
            LocalDate fecha = PickerFecha.getValue();
            String hora = CmbxHora.getValue();

            if(pelicula == null || sala == null || fecha == null || hora == null){
                System.out.println("Completa todos los campos");
                return;
            }

            Cartelera nuevaFuncion = new Cartelera();
            nuevaFuncion.setIdpelicula(pelicula);
            nuevaFuncion.setIdsala(sala);
            nuevaFuncion.setFecha(fecha);
            nuevaFuncion.setHora(hora);
            carteleraRepository.addCartelera(nuevaFuncion);
            cargarFunciones();
            limpiarCampos();
            System.out.println("Función agregada");

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    public void BtnEliminarFuncionAction(ActionEvent actionEvent) {
        Cartelera seleccionada = TableFunciones.getSelectionModel().getSelectedItem();

        if(seleccionada == null){
            System.out.println("Selecciona una función");
            return;
        }
        carteleraRepository.removeCartelera(seleccionada);
        cargarFunciones();
        listaUsuarios.clear();
        System.out.println("Función eliminada");
    }

    @FXML
    public void BtnActualizarAction(ActionEvent actionEvent) {
        cargarFunciones();
        listaUsuarios.clear();
        System.out.println("Datos actualizados");
    }

    @FXML
    public void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/admin_6.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Administrador");
        stage.setScene(nextScene);
    }

    @FXML
    public void CmbxSalaAction(ActionEvent actionEvent) {

    }

    private void limpiarCampos() {

        CmbxPelicula.getSelectionModel().clearSelection();
        CmbxSala.getSelectionModel().clearSelection();
        CmbxHora.getSelectionModel().clearSelection();
        PickerFecha.setValue(null);
    }
}