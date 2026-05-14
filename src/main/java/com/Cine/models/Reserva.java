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
        this.idusuario = idUsuario;
        this.idcartelera = idCartelera;
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
    private Usuario idusuario;
    public Usuario getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @ManyToOne
    @JoinColumn(name = "Cartelera_idCartelera", referencedColumnName = "idCartelera")
    private Cartelera idcartelera;
    public Cartelera getIdcartelera() {
        return idcartelera;
    }

    public void setIdcartelera(Cartelera idcartelera) {
        this.idcartelera = idcartelera;
    }
    @OneToMany(mappedBy = "idreserva", cascade = CascadeType.ALL)
    private List<Boleto> boletos = new ArrayList<>();
    public List<Boleto> getBoletos() {
        return boletos;
    }
    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

}