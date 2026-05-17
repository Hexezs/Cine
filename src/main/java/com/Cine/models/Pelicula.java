package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idpelicula;
    private String nombre;
    private int tiempo;
    private String imagenURL;
    private String sinopsis;
    public Pelicula() {}

    public Pelicula(String nombre, int tiempo, Idioma idIdioma, String sinopsis, ClasificacionRTC idClasificacionRTC, String imagenURL) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.idIdioma = idIdioma;
        this.sinopsis = sinopsis;
        this.idClasificacionRTC = idClasificacionRTC;
        this.imagenURL = imagenURL;
    }
    public int getIdpelicula() {
        return idpelicula;
    }

    private void setIdpelicula(int idpelicula) {
        this.idpelicula = idpelicula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    @OneToMany(mappedBy = "idpelicula")
    private List<Cartelera> funciones = new ArrayList<>();
    public List<Cartelera> getFunciones() {
        return funciones;
    }
    public void setFunciones(List<Cartelera> funciones) {
        this.funciones = funciones;
    }
    @ManyToOne
    @JoinColumn(name = "Idioma_IdIdioma", referencedColumnName = "idIdioma")
    private Idioma idIdioma;
    public Idioma getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Idioma idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagenURL(){
        return imagenURL;
    }
    public void setImagenURL(String imagenURL){
        this.imagenURL = imagenURL;
    }

    @ManyToOne
    @JoinColumn(name = "ClasificacionRTC_idClasificacionRTC", referencedColumnName = "idClasificacionRTC")
    private ClasificacionRTC idClasificacionRTC;
    public ClasificacionRTC getIdClasificacionRTC() {
        return idClasificacionRTC;
    }

    public void setIdClasificacionRTC(ClasificacionRTC idClasificacionRTC) {
        this.idClasificacionRTC = idClasificacionRTC;
    }
    @Override
    public String toString() {
        return nombre;
    }
}