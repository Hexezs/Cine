package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

    private int idReserva;
    private LocalDate fecha;
    private Usuario idUsuario;
    private Sala idSala;

    public Reserva() {}

    public Reserva(LocalDate fecha, Usuario idUsuario, Sala idSala) {
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idSala = idSala;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @ManyToOne
    @JoinColumn(name = "sala_idsala", referencedColumnName = "idsala")
    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }
}