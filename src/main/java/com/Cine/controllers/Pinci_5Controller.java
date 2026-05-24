//package com.Cine.controllers;
//import javafx.scene.image.Image;
//import com.Cine.MainApplication;
//import com.Cine.dto.PeliculaDTO;
//import com.Cine.models.Cartelera;
//import com.Cine.models.Pelicula;
//import com.Cine.services.PeliculaService;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.util.StringConverter;
//import com.Cine.utils.ImageUtils;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.List;
//public class Pinci_5Controller {
//
//    public GridPane GridPanePelicula;
//    public ImageView ImageView1;
//    public ImageView ImageView2;
//    public ImageView ImageView6;
//    public ImageView ImageView4;
//    public ImageView ImageView3;
//    public ImageView ImageView5;
//    public Label LabelNombrePeli1;
//    public Label LabelNombrePeli3;
//    public Label LabelNombrePeli4;
//    public Label LabelNombrePeli6;
//    public Label LabelNombrePeli2;
//    public Label LabelNombrePeli5;
//    @FXML private ComboBox<Pelicula> CmbxPelicula;
//    @FXML private DatePicker PickerDay;
//    @FXML private ComboBox<String> CmbxHorario;
//    @FXML private Button BtnAtras, BtnCancelar, BtnSig;
//    @FXML
//    private final PeliculaService peliculaService = new PeliculaService();
//
//    @FXML
//    public void initialize() {
//        cargarPeliculasEnGrid();
//        CmbxPelicula.getItems().addAll(peliculaService.obtenerPeliculas());
//        CmbxPelicula.setConverter(new StringConverter<>() {
//            @Override
//            public String toString(Pelicula p) {
//                return p == null ? "" : p.getNombre();
//            }
//
//            @Override
//            public Pelicula fromString(String s) {
//                return null;
//            }
//        });
//
//        CmbxPelicula.setOnAction(e -> cargarFechas());
//    }
//
//    @FXML
//    private void BtnSigAction(ActionEvent event) throws IOException {
//        Pelicula peli = CmbxPelicula.getValue();
//        LocalDate fecha = PickerDay.getValue();
//        String horario = CmbxHorario.getValue();
//        if (peli == null || fecha == null || horario == null) {
//            new Alert(Alert.AlertType.WARNING, "Completa todos los datos").showAndWait();
//            return;
//        }
//        Cartelera cartelera = peliculaService.buscarCartelera(peli.getIdpelicula(), fecha, horario);
//        if (cartelera == null) {
//            new Alert(Alert.AlertType.ERROR, "No se encontró la función").showAndWait();
//            return;
//        }
//
//        SelecPeli_7Controller.carteleraActual = cartelera;
//        SelecPeli_7Controller.usuarioLogueado = Use_4Controller.usuarioLogueado;
//        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/SelecPeli_7.fxml"));
//        Scene scene = new Scene(loader.load());
//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        stage.setTitle("CineSync - Selección de asientos");
//        stage.setScene(scene);
//    }
//
//    @FXML
//    private void BtnCancelarAction() {
//        CmbxPelicula.getSelectionModel().clearSelection();
//        PickerDay.setValue(null);
//        CmbxHorario.getItems().clear();
//    }
//
//    @FXML
//    private void BtnAtrasAction(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));
//        Scene scene = new Scene(loader.load());
//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//    }
//
//    private void cargarFechas() {
//        Pelicula peli = CmbxPelicula.getValue();
//        if (peli == null) return;
//        Set<LocalDate> fechas = new HashSet<>();
//        for (Cartelera c : peli.getFunciones()) {
//            fechas.add(c.getFecha());
//        }
//
//        PickerDay.setDayCellFactory(param -> new DateCell() {
//            @Override
//            public void updateItem(LocalDate item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) return;
//                if (!fechas.contains(item)) {
//                    setDisable(true);
//                    setStyle("-fx-background-color: #ffc0cb;");
//                }
//            }
//        });
//    }
//    @FXML
//    private void CmbxPeliculaAction(ActionEvent event) {
//        Pelicula pelicula = CmbxPelicula.getValue();
//        if(pelicula == null){
//            return;
//        }
//        Set<LocalDate> fechas = new HashSet<>();
//        for(Cartelera funcion : pelicula.getFunciones()){
//            fechas.add(funcion.getFecha());
//        }
//        PickerDay.setDayCellFactory(param -> new DateCell(){
//            @Override
//            public void updateItem(LocalDate fecha, boolean empty){
//                super.updateItem(fecha, empty);
//                if(empty){
//                    return;
//                }
//                if(!fechas.contains(fecha)){
//                    setDisable(true);
//                    setStyle("-fx-background-color: #ffc0cb;");
//                }
//            }
//        });
//    }
//    @FXML
//    private void PickerDayAction(ActionEvent event) {
//        Pelicula peli = CmbxPelicula.getValue();
//        LocalDate fecha = PickerDay.getValue();
//        if (peli == null || fecha == null) return;
//        CmbxHorario.getItems().clear();
//        for (Cartelera c : peli.getFunciones()) {
//            if (c.getFecha().equals(fecha)) {
//                CmbxHorario.getItems().add(c.getHora());
//            }
//        }
//    }
//    @FXML
//    private void cargarPeliculasEnGrid() {
//
//        GridPanePelicula.getChildren().clear();
//
//        List<PeliculaDTO> peliculas = peliculaService.obtenerPeliculasDTO();
//
//        if (peliculas == null || peliculas.isEmpty()) {
//            return;
//        }
//
//        int col = 0;
//        int row = 0;
//        int maxCols = 2;
//
//        int limite = Math.min(6, peliculas.size());
//
//        for (int i = 0; i < limite; i++) {
//
//            PeliculaDTO peli = peliculas.get(i);
//
//            ImageView imageView = new ImageView();
//            imageView.setImage(ImageUtils.fromBytes(peli.imagen()));
//            imageView.setFitWidth(140);
//            imageView.setFitHeight(180);
//            imageView.setPreserveRatio(true);
//
//            Label nombre = new Label(peli.nombre());
//            nombre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
//            nombre.setWrapText(true);
//
//            VBox box = new VBox(8);
//            box.setStyle("-fx-alignment: center;");
//            box.getChildren().addAll(imageView, nombre);
//
//            GridPanePelicula.add(box, col, row);
//
//            col++;
//            if (col >= maxCols) {
//                col = 0;
//                row++;
//            }
//        }
//    }
//}
package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Pelicula;
import com.Cine.services.PeliculaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;

public class Pinci_5Controller {
    @FXML
    private FlowPane FlowCartelera;
    @FXML private Label LblPeliSelec;
    @FXML private TableView<CarteleraDTO> TblFunciones;
    @FXML private TableColumn<CarteleraDTO, String> ColFecha;
    @FXML private TableColumn<CarteleraDTO, String> ColHora;
    @FXML private TableColumn<CarteleraDTO, String> ColSala;
    @FXML private Button BtnAtras, BtnCancelar, BtnSig;

    private final PeliculaService peliculaService = new PeliculaService();

    private Pelicula peliculaSeleccionada;
    private CarteleraDTO funcionSeleccionada;

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
                new SimpleStringProperty(c.getValue().fecha().toString())
        );

        ColHora.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().hora())
        );

        ColSala.setCellValueFactory(c ->
                new SimpleStringProperty("Sala " + c.getValue().idsala())
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
                cargarFunciones(p.getIdpelicula());
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
    private void cargarFunciones(int idPelicula) {

        List<CarteleraDTO> funciones =
                peliculaService.obtenerFuncionesDTO(idPelicula);

        TblFunciones.setItems(
                FXCollections.observableArrayList(funciones)
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
        controller.setCarteleraDTO(funcionSeleccionada);
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
    private void BtnAtrasAction(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                MainApplication.class.getResource("views/Use_4.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}