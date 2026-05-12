package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;

@Entity
@Table(name = "cartelera")
public class Cartelera {
    private int idCartelera;
    private LocalDate fecha;
    private String hora;
    private Pelicula Pelicula;
    private Sala idSala;

    public Cartelera(){}

    public Cartelera(LocalDate fecha, String hora, Pelicula pelicula, Sala sala) {
        this.fecha = fecha;
        this.hora = hora;
        this.pelicula=pelicula;
        this.sala=sala;

    }

    @Id@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdCartelera(){return idCartelera;}
    private void setIdCartelera(int idCartelera){this.idCartelera = idCartelera;}


}
