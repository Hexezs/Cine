package com.Cine.services;

import com.Cine.dto.CarteleraDTO;
import com.Cine.dto.PeliculaDTO;
import com.Cine.dto.PeliculaRegistroDTO;
import com.Cine.mapper.CarteleraMapper;
import com.Cine.mapper.PeliculaMapper;
import com.Cine.models.Cartelera;
import com.Cine.models.ClasificacionRTC;
import com.Cine.models.Idioma;
import com.Cine.models.Pelicula;
import com.Cine.repository.CarteleraRepository;
import com.Cine.repository.ClasificacionRTCRepository;
import com.Cine.repository.IdiomaRepository;
import com.Cine.repository.PeliculaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PeliculaService {

    private final PeliculaRepository peliculaRepository = new PeliculaRepository();
    private final ClasificacionRTCRepository clasificacionRepository = new ClasificacionRTCRepository();
    private final IdiomaRepository idiomaRepository = new IdiomaRepository();
    private final CarteleraRepository carteleraRepository = new CarteleraRepository();

    // --------------------------
    // AGREGAR PELÍCULA
    // --------------------------
    public void agregarPelicula(PeliculaRegistroDTO dto) {
        ClasificacionRTC clasificacion = clasificacionRepository.getClasificacionByID(dto.idClasificacionRTC());
        Idioma idioma = idiomaRepository.getIdiomaByID(dto.idIdioma());
        Pelicula pelicula = PeliculaMapper.aEntidad(dto, clasificacion, idioma);
        peliculaRepository.addPelicula(pelicula);
    }

    // --------------------------
    // ELIMINAR PELÍCULA
    // --------------------------
    public void eliminarPelicula(Pelicula pelicula) {
        peliculaRepository.removePelicula(pelicula);
    }



    // --------------------------
    // OBTENER PELÍCULAS DTO
    // --------------------------
    public List<PeliculaDTO> obtenerPeliculasDTO() {
        List<Pelicula> peliculas = peliculaRepository.getAllPeliculas();
        List<PeliculaDTO> lista = new ArrayList<>();
        for (Pelicula p : peliculas) {
            lista.add(PeliculaMapper.aDTO(p));
        }
        return lista;
    }
    public List<Pelicula> obtenerPeliculasConCartelera() {
        return peliculaRepository.getPeliculasConCartelera();
    }
    // --------------------------
    // BUSCAR POR NOMBRE
    // --------------------------
    public Pelicula buscarPorNombre(String nombre) {
        return peliculaRepository.getPeliculaByNombre(nombre);
    }
    public List<Pelicula> obtenerPeliculasConFunciones() {

        List<Cartelera> funciones = carteleraRepository.getAllCartelera();

        List<Pelicula> peliculas = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();

        for (Cartelera c : funciones) {

            Pelicula p = c.getIdpelicula();

            if (!ids.contains(p.getIdpelicula())) {
                ids.add(p.getIdpelicula());
                peliculas.add(p);
            }
        }

        return peliculas;
    }
    // --------------------------
    // FUNCIONES POR PELÍCULA
    // --------------------------
    public List<CarteleraDTO> obtenerFuncionesDTO(int idPelicula) {

        List<Cartelera> funciones =
                carteleraRepository.getCarteleraByPelicula(idPelicula);

        return funciones.stream()
                .map(c -> new CarteleraDTO(
                        c.getIdCartelera(),
                        c.getFecha(),
                        c.getHora().toString(),
                        c.getIdpelicula().getIdpelicula(),
                        c.getIdsala().getIdsala()
                ))
                .toList();
    }

    // --------------------------
    // BUSCAR CARTELERA ESPECÍFICA
    // --------------------------
    public Cartelera buscarCartelera(int idPelicula, LocalDate fecha, String hora) {
        List<Cartelera> funciones = carteleraRepository.getCarteleraByPelicula(idPelicula);
        for (Cartelera c : funciones) {
            if (c.getFecha().equals(fecha) && c.getHora().equals(hora)) {
                return c;
            }
        }
        return null;
    }

    // --------------------------
    // FECHAS DISPONIBLES
    // --------------------------
    public List<LocalDate> obtenerFechasPorPelicula(int idPelicula) {
        List<Cartelera> funciones = carteleraRepository.getCarteleraByPelicula(idPelicula);
        List<LocalDate> fechas = new ArrayList<>();
        for (Cartelera c : funciones) {
            if (!fechas.contains(c.getFecha())) {
                fechas.add(c.getFecha());
            }
        }
        return fechas;
    }

    // --------------------------
    // HORARIOS POR FECHA
    // --------------------------
    public List<String> obtenerHorarios(int idPelicula, LocalDate fecha) {
        List<Cartelera> funciones = carteleraRepository.getCarteleraByPelicula(idPelicula);
        List<String> horarios = new ArrayList<>();
        for (Cartelera c : funciones) {
            if (c.getFecha().equals(fecha)) {
                horarios.add(c.getHora());
            }
        }
        return horarios;
    }
    public List<Pelicula> obtenerPeliculas() {
        return peliculaRepository.getAllPeliculas();
    }
}