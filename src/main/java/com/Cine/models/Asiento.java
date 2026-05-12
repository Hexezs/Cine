package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Asiento")
public class Asiento {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idAsiento;
    private String numero;
    private String letra;

    public Asiento() {}
    public Asiento(String numero, String letra) {
        this.numero = numero;
        this.letra = letra; }
    public int getIdAsiento() {
        return idAsiento;
    }
    private void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    @ManyToOne
    @JoinColumn(name = "sala_id_sala", referencedColumnName = "idasiento")
    private Sala sala;
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
}