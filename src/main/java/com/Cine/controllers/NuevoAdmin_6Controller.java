package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.models.*;
import com.Cine.repository.*;
import com.Cine.services.PeliculaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class NuevoAdmin_6Controller {
    @FXML
    private Button BtnElimFuncion;
    @FXML
    private byte[] imagenBytes;
    @FXML
    private ComboBox<Pelicula> CmbxPelicula;
    @FXML
    private DatePicker PickerDay;
    @FXML
    private ComboBox<Sala> CmbxSala;
    @FXML
    private ComboBox<String> CmbxHorario;
    @FXML
    private Button BtnAgregarFun;
    @FXML
    private Button BtnElimPeli;
    @FXML
    private TableView<Cartelera> TableFunciones;
    @FXML
    private TableColumn<Cartelera, String> ColumIdFuncion;
    @FXML
    private TableColumn<Cartelera, String> ColumPelicula;
    @FXML
    private TableColumn<Cartelera, String> ColumFecha;
    @FXML
    private TableColumn<Cartelera, String> ColumSala;
    @FXML
    private TableColumn<Cartelera, String> ColumHora;
    @FXML
    private TextField TextNombrePeli;
    @FXML
    private TextField TextSinopsis;
    @FXML
    private ComboBox<ClasificacionRTC> CmbxClasificacion;
    @FXML
    private ComboBox<Idioma> CmbxIdioma;
    @FXML
    private TextField TextTiempo;
    @FXML
    private Button BtnAgregarPeli;
    @FXML
    private ImageView ImgPreview;
    @FXML
    private Button BtnSubirImg;
    @FXML
    private ComboBox<Pelicula> CmbxElimPelicula;
    @FXML
    private Button BtnSig;
    @FXML
    private Button BtnAtras;
    private final ReservaRepository reservaRepository = new ReservaRepository();
    private final PeliculaService peliculaService = new PeliculaService();

    private final IdiomaRepository idiomaRepository = new IdiomaRepository();

    private final ClasificacionRTCRepository clasificacionRepository = new ClasificacionRTCRepository();
    private final PeliculaRepository peliculaRepository = new PeliculaRepository();

    private final SalaRepository salaRepository = new SalaRepository();

    private final CarteleraRepository carteleraRepository = new CarteleraRepository();

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    private ObservableList<Cartelera> listaFunciones = FXCollections.observableArrayList();

    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        CmbxHorario.getItems().addAll(
                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00",
                "20:00",
                "22:00"
        );
        CmbxSala.getItems().addAll(salaRepository.getAllSalas());
        CmbxIdioma.getItems().addAll(idiomaRepository.getAllIdiomas());

        CmbxClasificacion.getItems().addAll(clasificacionRepository.getAllClasificaciones());
        CmbxPelicula.getItems().addAll(peliculaService.obtenerPeliculas());

        CmbxPelicula.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });

        CmbxPelicula.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });
        CmbxElimPelicula.getItems().addAll(peliculaService.obtenerPeliculas());

        CmbxElimPelicula.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });

        CmbxElimPelicula.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pelicula item, boolean empty) {super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });
        configurarTablaFunciones();
        cargarFunciones();
    }
    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Cine-Sync Iniciar Sesion");
        stage.setScene(nextScene);
    }
    @FXML
    public void BtnAtrasAction(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Cine-Sync Iniciar Sesion");
        stage.setScene(nextScene);
    }
    @FXML
    public void BtnAgregaPeliAction(ActionEvent actionEvent) {

        try {

            String nombre = TextNombrePeli.getText();
            Idioma idioma = CmbxIdioma.getValue();
            String sinopsis = TextSinopsis.getText();
            ClasificacionRTC clasificacion = CmbxClasificacion.getValue();

            if (nombre.isEmpty() || idioma == null || clasificacion == null) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Completa todos los campos");
                alerta.showAndWait();
                return;
            }

            Pelicula pelicula = new Pelicula();
            pelicula.setNombre(nombre);
            pelicula.setTiempo(120);
            pelicula.setSinopsis(sinopsis);
            pelicula.setImagen(imagenBytes);
            pelicula.setIdIdioma(idioma);
            pelicula.setIdClasificacionRTC(clasificacion);
            peliculaService.agregarPelicula(pelicula);
            Alert exito = new Alert(Alert.AlertType.INFORMATION);
            exito.setTitle("Éxito");
            exito.setHeaderText(null);
            exito.setContentText("Película guardada correctamente");
            exito.showAndWait();
            CmbxPelicula.getItems().clear();
            CmbxPelicula.getItems().addAll(peliculaService.obtenerPeliculas());
            CmbxElimPelicula.getItems().clear();
            CmbxElimPelicula.getItems().addAll(peliculaService.obtenerPeliculas());
            TextNombrePeli.clear();
            TextSinopsis.clear();
            TextTiempo.clear();
            CmbxIdioma.getSelectionModel().clearSelection();
            CmbxClasificacion.getSelectionModel().clearSelection();
            ImgPreview.setImage(null);

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("No se pudo guardar la película");
            error.showAndWait();
            e.printStackTrace();
        }
    }
    @FXML
    public void BtnSubirImgAction(ActionEvent actionEvent) {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
            File archivo = fileChooser.showOpenDialog(null);
            if (archivo != null) {
                imagenBytes = Files.readAllBytes(archivo.toPath());
                Image image = new Image(new FileInputStream(archivo));
                ImgPreview.setImage(image);
                System.out.println("Imagen cargada");

            }
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
    @FXML
    public void BtnAgregaFunAction(ActionEvent actionEvent) {

        try {
            Pelicula pelicula = CmbxPelicula.getValue();
            Sala sala = CmbxSala.getValue();
            String hora = CmbxHorario.getValue();
            if (pelicula == null || sala == null || hora == null || PickerDay.getValue() == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Completa todos los campos");
                alerta.showAndWait();
                return;
            }

            Cartelera nuevaFuncion = new Cartelera();
            nuevaFuncion.setIdpelicula(pelicula);
            nuevaFuncion.setIdsala(sala);
            nuevaFuncion.setFecha(PickerDay.getValue());
            nuevaFuncion.setHora(hora);
            carteleraRepository.addCartelera(nuevaFuncion);
            cargarFunciones();
            TableFunciones.refresh();
            limpiarCamposFuncion();
            Alert exito = new Alert(Alert.AlertType.INFORMATION);
            exito.setTitle("Éxito");
            exito.setHeaderText(null);
            exito.setContentText("Función agregada correctamente");
            exito.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("No se pudo agregar la función");
            error.showAndWait();
        }
    }
    @FXML
    public void BtnElimFuncionAction(ActionEvent actionEvent) {
        try {
            Cartelera funcionSeleccionada = TableFunciones.getSelectionModel().getSelectedItem();
            if (funcionSeleccionada == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Selecciona una función");
                alerta.showAndWait();
                return;
            }

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmación");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("La función y sus reservas serán eliminadas");
            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                reservaRepository.eliminarReservasPorCartelera(funcionSeleccionada.getIdCartelera());
                carteleraRepository.removeCartelera(funcionSeleccionada);
                listaFunciones.clear();
                listaFunciones.addAll(carteleraRepository.getAllCartelera());
                TableFunciones.refresh();
                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Éxito");
                exito.setHeaderText(null);
                exito.setContentText("Función eliminada correctamente");
                exito.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("No se pudo eliminar la función");
            error.showAndWait();
        }
    }
    @FXML
    public void BtnElimPeliAction(ActionEvent actionEvent) {
        try {
            Pelicula peliculaSeleccionada = CmbxElimPelicula.getValue();
            if (peliculaSeleccionada == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Selecciona una película");
                alerta.showAndWait();
                return;
            }

            if (peliculaSeleccionada.getFunciones() != null && !peliculaSeleccionada.getFunciones().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("No se puede eliminar");
                error.setHeaderText(null);
                error.setContentText("La película está en una cartelera.\n" + "Primero elimina la cartelera.");
                error.showAndWait();
                return;
            }

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmación");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("¿Estás seguro de eliminar esta película?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                peliculaService.eliminarPelicula(peliculaSeleccionada);
                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Éxito");
                exito.setHeaderText(null);
                exito.setContentText("Película eliminada correctamente");
                exito.showAndWait();
                CmbxElimPelicula.getItems().clear();
                CmbxElimPelicula.getItems().addAll(peliculaService.obtenerPeliculas());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void CmbxClasificacionAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxIdiomaAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxPeliculaAction(ActionEvent actionEvent) {

    }
    @FXML
    public void CmbxHorarioAction(ActionEvent actionEvent) {
    }
    @FXML
    public void PickerDayAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxSalaAction(ActionEvent actionEvent) {
    }
    @FXML
    public void CmbxElimPeliculaAction(ActionEvent actionEvent) {
        Pelicula pelicula = CmbxElimPelicula.getValue();
        if(pelicula != null && pelicula.getImagen() != null){
            ByteArrayInputStream bis = new ByteArrayInputStream(pelicula.getImagen());
            Image image = new Image(bis);
            ImgPreview.setImage(image);
            System.out.println("Imagen cargada desde DB");
        }
    }
    private void configurarTablaFunciones() {
        ColumIdFuncion.setCellValueFactory(new PropertyValueFactory<>("idCartelera"));
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColumHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        ColumPelicula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdpelicula().getNombre()));
        ColumSala.setCellValueFactory(cellData -> new SimpleStringProperty("Sala " + cellData.getValue().getIdsala().getIdsala()));
        TableFunciones.setItems(listaFunciones);
    }

    private void cargarFunciones() {
        listaFunciones.clear();
        listaFunciones.addAll(carteleraRepository.getAllCartelera());
    }

    private void limpiarCamposFuncion() {
        CmbxPelicula.getSelectionModel().clearSelection();
        CmbxSala.getSelectionModel().clearSelection();
        CmbxHorario.getSelectionModel().clearSelection();
        PickerDay.setValue(null);
    }
}
