package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TipoSala")
public class TipoSala {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idTipoSala;
    private String nombreTipoSala, descripcion;

    public TipoSala() {}

    public TipoSala(String nombreTipoSala, String descripcion) {
        this.nombreTipoSala = nombreTipoSala;
        this.descripcion = descripcion;
    }
    public int getIdTipoSala() {
        return idTipoSala;
    }
    private void setIdTipoSala(int idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public String getNombreTipoSala() {
        return nombreTipoSala;
    }
    public void setNombreTipoSala(String nombreTipoSala) {
        this.nombreTipoSala = nombreTipoSala;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}