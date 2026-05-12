package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "boleto")
public class Boleto {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idBoleto;
    private int cantidad;
    private int monto;
    private String nombreasiento;
    public Boleto() {}

    public Boleto(int cantidad, int monto,String nombreasiento, Asiento asiento, Reserva reserva) {
        this.cantidad = cantidad;
        this.monto = monto;
        this.nombreasiento=nombreasiento;
        this.asiento = asiento;
        this.reserva = reserva;
    }
    public int getIdBoleto() {
        return idBoleto;
    }
    public void setIdBoleto(int idBoleto) {
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
    public String getNombreasiento(){return nombreasiento;}
    public void setNombreasiento(String nombreasiento){this.nombreasiento=nombreasiento;}

    @ManyToOne
    @JoinColumn(name = "reserva_idreserva", referencedColumnName = "idreserva")
    private Reserva reserva;
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @ManyToOne
    @JoinColumn(name = "asiento_idasiento", referencedColumnName = "idasiento")
    private Asiento asiento;
    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

}