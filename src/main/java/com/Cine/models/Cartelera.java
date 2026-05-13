package com.Cine.models;

import com.Cine.repository.CarteleraRepository;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table (name ="Cartelera")
public class Cartelera {
    private LocalDate fecha;
    private String hora;

    public Cartelera(){}
    public Cartelera(LocalDate fecha, String hora, Pelicula idpelicula, Sala idsala){
        this.fecha=fecha;
        this.hora=hora;
        this.idpelicula=idpelicula;
        this.idsala=idsala;
    }
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idCartelera;
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
    @JoinColumn(name = "sala_idsala", referencedColumnName = "idsala", foreignKey = @ForeignKey (name = "idsala"))
    private Sala idsala;
    public Sala getIdsala() {
        return idsala;
    }

    public void setIdsala(Sala idsala) {
        this.idsala = idsala;
    }

    @ManyToOne
    @JoinColumn(name = "pelicula_idpelicula", referencedColumnName = "idpelicula", foreignKey = @ForeignKey (name = "idpelicula"))
    private Pelicula idpelicula;
    public Pelicula getIdpelicula() {
        return idpelicula;
    }

    public void setIdpelicula(Pelicula idpelicula) {
        this.idpelicula = idpelicula;
    }
}
