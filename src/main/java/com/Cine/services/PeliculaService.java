package com.Cine.services;
//falta el Repository para que los metodos funcionen, es decir
//en repositorio se crean esos metodos
import com.Cine.models.Pelicula;

import java.util.List;
import java.util.Optional;

public class PeliculaService {
    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository = peliculaRepository;
    }

    //Crear o Guardar
    public Pelicula guardarPelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    //leer_todo
    public List<Pelicula> obtenerTodasLasPeliculas(){
        return peliculaRepository.findAll();
    }

    //leer por id (optional -> prevenir errores de valores nulos si no existe)
    public Optional<Pelicula> obtenerPeliculaPorId(int id){
        return peliculaRepository.findById(id);
    }

    //actualizar usando ifelse+excepcion
    public Pelicula actualizarPelicula(int id, Pelicula peliculaActualizada){
        return peliculaRepository.findById(id).map(pelicula -> {
            pelicula.setTitulo(peliculaActualizada.getTitulo());
            pelicula.setDuracion(peliculaActualizada.getDuracion());
            return peliculaRepository.save(pelicula);
        }).orElseThrow(()-> new RuntimeException("Pelicula no encontrada"));
    }

    //eliminar
    public void eliminarPelicula(int id){
        peliculaRepository.deleteById(id);
    }
}
