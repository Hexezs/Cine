package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Pelicula")
public class Pelicula {

    private int idPelicula;
    private String nombre;
    private int tiempo;
    private Idioma idIdioma;
    private String sinopsis;
    private ClasificacionRTC idClasificacionRTC;

    public Pelicula() {}

    public Pelicula(String nombre, int tiempo, Idioma idIdioma, String sinopsis, ClasificacionRTC idClasificacionRTC) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.idIdioma = idIdioma;
        this.sinopsis = sinopsis;
        this.idClasificacionRTC = idClasificacionRTC;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdPelicula() {
        return idPelicula;
    }

    private void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
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
    @ManyToOne
    @JoinColumn(name = "Idioma_IdIdioma", referencedColumnName = "idIdioma")
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

    @ManyToOne
    @JoinColumn(name = "ClasificacionRTC_idClasificacionRTC", referencedColumnName = "idClasificacionRTC")
    public ClasificacionRTC getIdClasificacionRTC() {
        return idClasificacionRTC;
    }

    public void setIdClasificacionRTC(ClasificacionRTC idClasificacionRTC) {
        this.idClasificacionRTC = idClasificacionRTC;
    }
}