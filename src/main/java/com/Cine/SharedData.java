package com.Cine;

import com.Cine.models.Usuario;
import com.Cine.models.Reserva;
import com.Cine.models.Pelicula;
import com.Cine.models.Cartelera;
import com.Cine.models.Boleto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedData {
    private static SharedData instance;

    // Listas globales (Simulan la base de datos en memoria)
    private List<Usuario> listaUsuariosGlobal = new ArrayList<>();
    private List<Reserva> historialCompras = new ArrayList<>();
    private List<Pelicula> listaPeliculasGlobal = new ArrayList<>();

    private void cargarCatalogoInicial() {
        // Creamos la lista de nombres que me pasaste
        List<String> nombres = Arrays.asList(
                "Gato 1",
                "Gato 2",
                "Gato 3",
                "Gato: La Precuela",
                "Gato: La Venganza",
                "Gato Contraataca"
        );

        for (String nombre : nombres) {
            Pelicula p = new Pelicula();
            p.setNombre(nombre);
            p.setTiempo(120); // Tiempo estándar de 2 horas por ahora
            p.setSinopsis("Una emocionante aventura de la saga " + nombre);

            listaPeliculasGlobal.add(p);
        }
    }
    // Sesión del usuario
    private Usuario usuarioLogueado;
    private boolean modoEdicion = false;

    // Datos temporales de la reserva en curso
    // CAMBIO: Ahora guardamos el objeto Pelicula y Cartelera, no solo el nombre
    private Pelicula peliculaSeleccionada;
    private Cartelera carteleraSeleccionada;
    private LocalDate fechaSeleccionada;
    private int cantidadBoletos;
    private List<Boleto> boletosTemporales = new ArrayList<>();

    private SharedData() {cargarCatalogoInicial();}

    public static SharedData getInstance() {
        if (instance == null) instance = new SharedData();
        return instance;
    }

    // --- MÉTODOS DE USUARIO ---
    public void registrarNuevoUsuario(Usuario nuevo) {
        this.listaUsuariosGlobal.add(nuevo);
    }

    public Usuario buscarUsuario(String correo, String password) {
        return listaUsuariosGlobal.stream()
                .filter(u -> u.getCorreo().equals(correo) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // --- MÉTODOS DE RESERVA ---
    public void agregarAlHistorial(Reserva r) {
        this.historialCompras.add(r);
    }

    public void limpiarSesion() {
        this.usuarioLogueado = null;
        this.modoEdicion = false;
        limpiarReserva();
    }

    public void limpiarReserva() {
        this.peliculaSeleccionada = null;
        this.carteleraSeleccionada = null;
        this.fechaSeleccionada = null;
        this.cantidadBoletos = 0;
        this.boletosTemporales.clear();
    }

    // --- GETTERS Y SETTERS ACTUALIZADOS ---
    public List<Usuario> getListaUsuariosGlobal() { return listaUsuariosGlobal; }

    public List<Reserva> getHistorialCompras() { return historialCompras; }
    public List<Pelicula> getListaPeliculasGlobal() {
        return listaPeliculasGlobal;
    }
    public void agregarPeliculaAlCatalogo(Pelicula p) {
        this.listaPeliculasGlobal.add(p);
    }
    public Usuario getUsuarioLogueado() { return usuarioLogueado; }
    public void setUsuarioLogueado(Usuario usuarioLogueado) { this.usuarioLogueado = usuarioLogueado; }

    public boolean isModoEdicion() { return modoEdicion; }
    public void setModoEdicion(boolean modoEdicion) { this.modoEdicion = modoEdicion; }

    public Pelicula getPeliculaSeleccionada() { return peliculaSeleccionada; }
    public void setPeliculaSeleccionada(Pelicula peliculaSeleccionada) { this.peliculaSeleccionada = peliculaSeleccionada; }

    public Cartelera getCarteleraSeleccionada() { return carteleraSeleccionada; }
    public void setCarteleraSeleccionada(Cartelera carteleraSeleccionada) { this.carteleraSeleccionada = carteleraSeleccionada; }

    public LocalDate getFechaSeleccionada() { return fechaSeleccionada; }
    public void setFechaSeleccionada(LocalDate fechaSeleccionada) { this.fechaSeleccionada = fechaSeleccionada; }

    public int getCantidadBoletos() { return cantidadBoletos; }
    public void setCantidadBoletos(int cantidadBoletos) { this.cantidadBoletos = cantidadBoletos; }

    public List<Boleto> getBoletosTemporales() { return boletosTemporales; }
    public void setBoletosTemporales(List<Boleto> boletosTemporales) { this.boletosTemporales = boletosTemporales; }
}