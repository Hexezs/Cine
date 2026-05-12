package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table (name ="Cartelera")
public class Cartelera {
    private int idCartelera;
    private LocalDate fecha;
    private String hora;
    private Sala idsala;
    private Pelicula idpelicula;

    public Cartelera(){}
    public Cartelera(LocalDate fecha, String hora){
        this.fecha=fecha;
        this.hora=hora;
    }
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdCartelera(){return idCartelera;}
    public void setIdCartelera(int idCartelera){this.idCartelera=idCartelera;}
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getHora(){return hora;}
    public void setHora(String hora){this.hora=hora;}

    @ManyToOne
    @JoinColumn(name = "sala_idsala", referencedColumnName = "idsala")
    public Sala getIdsala() {
        return idsala;
    }

    public void setIdsala(Sala idsala) {
        this.idsala = idsala;
    }

    @ManyToOne
    @JoinColumn(name = "pelicula_idpelicula", referencedColumnName = "idpelicula")
    public Pelicula getIdpelicula() {
        return idpelicula;
    }

    public void setIdpelicula(Pelicula idpelicula) {
        this.idpelicula = idpelicula;
    }
}
