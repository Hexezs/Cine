package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "reserva" )
public class Reserva {
    private int idreserva;
    private Instant fecha;

    public Reserva(){
    }

    public Reserva(Instant fecha){
   this.fecha= fecha;
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha.toInstant();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reserva_idreserva", referencedColumnName = "idboleto")
    private List<Boleto> boleto = new ArrayList<>();



}
