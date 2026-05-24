package com.Cine.controllers;
import com.Cine.MainApplication;
import com.Cine.dto.CarteleraDTO;
import com.Cine.dto.CarteleraRegistroDTO;
import com.Cine.dto.PeliculaRegistroDTO;
import com.Cine.models.*;
import com.Cine.repository.*;
import com.Cine.services.CarteleraService;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
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
    @FXML
    private final List<String> horariosBase = List.of(
            "10:00","12:00","14:00","16:00","18:00","20:00","22:00"
    );
    @FXML
    private final ReservaRepository reservaRepository = new ReservaRepository();
    @FXML
    private final PeliculaService peliculaService = new PeliculaService();
    @FXML
    private final IdiomaRepository idiomaRepository = new IdiomaRepository();
    @FXML
    private final ClasificacionRTCRepository clasificacionRepository = new ClasificacionRTCRepository();
    @FXML
    private final PeliculaRepository peliculaRepository = new PeliculaRepository();
    @FXML
    private final SalaRepository salaRepository = new SalaRepository();
    @FXML
    private final CarteleraRepository carteleraRepository = new CarteleraRepository();
    @FXML
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    @FXML
    private ObservableList<Cartelera> listaFunciones = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    @FXML
    private CarteleraService carteleraService = new CarteleraService();
    @FXML
    public void initialize(){

        PickerDay.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

        CmbxHorario.getItems().addAll(horariosBase);
        CmbxSala.getItems().addAll(salaRepository.getAllSalas());
        CmbxIdioma.getItems().addAll(idiomaRepository.getAllIdiomas());
        CmbxClasificacion.getItems().addAll(clasificacionRepository.getAllClasificaciones());
        CmbxPelicula.getItems().addAll(peliculaService.obtenerPeliculas());

        configurarTablaFunciones();
        cargarFunciones();
    }
    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync -Iniciar Sesion");
        stage.setScene(nextScene);
    }
    @FXML
    public void BtnAtrasAction(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync -Iniciar Sesion");
        stage.setScene(nextScene);
    }
    @FXML
    public void BtnAgregaPeliAction(ActionEvent actionEvent) {
        int tiempo;
        try {

            if (TextNombrePeli.getText().isEmpty()
                    || TextTiempo.getText().isEmpty()
                    || CmbxIdioma.getValue() == null
                    || CmbxClasificacion.getValue() == null
                    || imagenBytes == null) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);

                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Completa todos los campos");

                alerta.showAndWait();

                return;
            }
            try {
                tiempo = Integer.parseInt(TextTiempo.getText());
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("El campo tiempo debe ser un número entero válido");
                alerta.showAndWait();
                return;
            }

            PeliculaRegistroDTO dto = new PeliculaRegistroDTO(
                    0,
                    TextNombrePeli.getText(),
                    tiempo,
                    TextSinopsis.getText(),
                    imagenBytes,
                    CmbxClasificacion.getValue().getIdClasificacionRTC(),
                    CmbxIdioma.getValue().getIdIdioma()
            );

            peliculaService.agregarPelicula(dto);

            Alert exito = new Alert(Alert.AlertType.INFORMATION);

            exito.setTitle("Éxito");
            exito.setHeaderText(null);
            exito.setContentText("Película guardada correctamente");

            exito.showAndWait();

            CmbxPelicula.getItems().clear();
            CmbxPelicula.getItems().addAll(peliculaService.obtenerPeliculas());
            CmbxElimPelicula.getItems().clear();
            CmbxElimPelicula.getItems().addAll(peliculaService.obtenerPeliculas()
            );
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
            LocalDate fecha = PickerDay.getValue();
            int duracion = pelicula.getTiempo();

            if (pelicula == null
                    || sala == null
                    || hora == null
                    || PickerDay.getValue() == null) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);

                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("Completa todos los campos");

                alerta.showAndWait();

                return;
            }
            List<Cartelera> funcionesSala = carteleraRepository.getFuncionesPorSalaYFecha(sala.getIdsala(), fecha);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime inicioSeleccion = LocalTime.parse(hora, formato);
            LocalTime finSeleccion = inicioSeleccion.plusMinutes(duracion);

            for (Cartelera c : funcionesSala) {
                LocalTime inicioExistente = LocalTime.parse(c.getHora(), formato);
                LocalTime finExistente = inicioExistente.plusMinutes(c.getIdpelicula().getTiempo());
                boolean conflicto = inicioSeleccion.isBefore(finExistente) && finSeleccion.isAfter(inicioExistente);
                if (conflicto) {
                    new Alert(Alert.AlertType.WARNING,
                            "La película se solapa con otra función en esta sala.").showAndWait();
                    return;
                }
            }
            CarteleraRegistroDTO dto = new CarteleraRegistroDTO(
                    0,
                    pelicula.getIdpelicula(),
                    sala.getIdsala(),
                    PickerDay.getValue(),
                    hora
            );
            carteleraService.guardarFuncion(dto);
            cargarFunciones();
            TableFunciones.refresh();
            limpiarCamposFuncion();
            CmbxHorario.getSelectionModel().clearSelection();
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
            List<Cartelera> funciones = carteleraRepository.getFuncionesPorPelicula(peliculaSeleccionada.getIdpelicula());

            if (!funciones.isEmpty()) {
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
    public void PickerDayAction(ActionEvent actionEvent) {
        actualizarHorariosDisponibles();
    }
    @FXML
    public void CmbxSalaAction(ActionEvent actionEvent) {
        actualizarHorariosDisponibles();
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
    @FXML
    private void configurarTablaFunciones() {
        ColumIdFuncion.setCellValueFactory(new PropertyValueFactory<>("idCartelera"));
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColumHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        ColumPelicula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdpelicula().getNombre()));
        ColumSala.setCellValueFactory(cellData -> new SimpleStringProperty("Sala " + cellData.getValue().getIdsala().getIdsala()));
        TableFunciones.setItems(listaFunciones);
    }
    @FXML
    private void cargarFunciones() {
        listaFunciones.clear();
        listaFunciones.addAll(carteleraRepository.getAllCartelera());
    }
    @FXML
    private void limpiarCamposFuncion() {
        CmbxPelicula.getSelectionModel().clearSelection();
        CmbxSala.getSelectionModel().clearSelection();
        CmbxHorario.getSelectionModel().clearSelection();
        PickerDay.setValue(null);
    }
    @FXML
    private void actualizarHorariosDisponibles() {

        Sala sala = CmbxSala.getValue();
        LocalDate fecha = PickerDay.getValue();

        if (sala == null || fecha == null) {
            CmbxHorario.getItems().setAll(horariosBase);
            return;
        }

        List<Cartelera> funciones =
                carteleraRepository.getFuncionesPorSalaYFecha(sala.getIdsala(), fecha);

        List<String> ocupados = funciones.stream()
                .map(Cartelera::getHora)
                .toList();

        List<String> disponibles = horariosBase.stream()
                .filter(h -> !ocupados.contains(h))
                .toList();

        CmbxHorario.getItems().setAll(disponibles);
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
}
