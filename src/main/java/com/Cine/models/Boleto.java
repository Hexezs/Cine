package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;

@Entity
@Table( name = "boleto" )
public class Boleto {
    private int idboleto;
    private int cantidad;
    private  int monto;
    private  String asiento;

    public Boleto(){
    }

    public Boleto(int cantidad, int monto, String asiento){
        this.cantidad= cantidad;
        this.monto=monto;
        this.asiento=asiento;
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getIdboleto(){return idboleto;}
    public void setIdboleto(int idboleto){this.idboleto=idboleto;}

    public int getCantidad(){return  cantidad;}
    public void setCantidad(int cantidad){this.cantidad=cantidad;}

    public int getMonto(){return monto;}
    public void setMonto(int monto){this.monto=monto;}

    public String getAsiento(){return  asiento;}
    public void setAsiento(String asiento){ this.asiento=asiento;}


}
