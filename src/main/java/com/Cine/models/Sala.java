package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idsala;
    private int capacidad;
    private TipoSala idTipoSala;

    public Sala() {}

    public Sala(int capacidad,  TipoSala idTipoSala) {
        this.capacidad = capacidad;
        this.idTipoSala = idTipoSala;
    }
    public int getIdsala() {
        return idsala;
    }

    private void setIdsala(int idsala) {
        this.idsala = idsala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Asiento> asientos = new ArrayList<>();
    public List<Asiento> getAsientos() { return asientos; }
    public void setAsientos(List<Asiento> asientos) { this.asientos = asientos; }

    @OneToMany(mappedBy = "sala")
    private List<Cartelera> funciones = new ArrayList<>();
    public List<Cartelera> getFunciones() { return funciones; }
    public void setFunciones(List<Cartelera> funciones) { this.funciones = funciones; }

    @ManyToOne
    @JoinColumn(name = "TipoSala_IdTipoSala", referencedColumnName = "idTipoSala")
    public TipoSala getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(TipoSala idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

}