package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "boleto")
public class Boleto {

    private int idBoleto;
    private int cantidad;
    private int monto;
    private String asiento;
    private Reserva idReserva;

    public Boleto() {}

    public Boleto(int cantidad, int monto, String asiento, Reserva idReserva) {
        this.cantidad = cantidad;
        this.monto = monto;
        this.asiento = asiento;
        this.idReserva = idReserva;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdBoleto() {
        return idBoleto;
    }
    private void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @ManyToOne
    @JoinColumn(name = "reserva_idreserva", referencedColumnName = "idreserva")
    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }
}