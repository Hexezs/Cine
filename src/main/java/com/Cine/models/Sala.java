package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sala")
public class Sala {

    private int idSala;
    private int capacidad;
    private String horario;
    private int disponibles;
    private Pelicula idPelicula;
    private TipoSala idTipoSala;
    private Asiento idAsiento;

    public Sala() {}

    public Sala(int capacidad, String horario, int disponibles, Pelicula idPelicula, TipoSala idTipoSala, Asiento idAsiento) {
        this.capacidad = capacidad;
        this.horario = horario;
        this.disponibles = disponibles;
        this.idPelicula = idPelicula;
        this.idTipoSala = idTipoSala;
        this.idAsiento = idAsiento;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdSala() {
        return idSala;
    }

    private void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    @ManyToOne
    @JoinColumn(name = "pelicula_idpelicula", referencedColumnName = "idpelicula")
    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    @ManyToOne
    @JoinColumn(name = "TipoSala_IdTipoSala", referencedColumnName = "idTipoSala")
    public TipoSala getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(TipoSala idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    @ManyToOne
    @JoinColumn(name = "Asiento_idasiento", referencedColumnName = "idasiento")
    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }
}