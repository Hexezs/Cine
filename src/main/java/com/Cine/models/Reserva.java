package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idReserva;
    private LocalDate fecha;
    public Reserva() {}

    public Reserva(LocalDate fecha, Usuario idUsuario, Cartelera idCartelera) {
        this.fecha = fecha;
        this.usuario = idUsuario;
        this.cartelera = idCartelera;
    }

    public int getIdReserva() {
        return idReserva;
    }

    private void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    @ManyToOne
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "Cartelera_idCartelera", referencedColumnName = "idCartelera")
    private Cartelera cartelera;
    public Cartelera getCartelera() {
        return cartelera;
    }

    public void setCartelera(Cartelera cartelera) {
        this.cartelera = cartelera;
    }
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Boleto> boletos = new ArrayList<>();
    public List<Boleto> getBoletos() {
        return boletos;
    }
    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

}