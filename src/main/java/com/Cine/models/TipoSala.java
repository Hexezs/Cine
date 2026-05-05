package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TipoSala")
public class TipoSala {

    private int idTipoSala;
    private String tradicional;
    private String vip;
    private String sala3D;
    private String sala4D;
    private String imax;

    public TipoSala() {}

    public TipoSala(String tradicional, String vip, String sala3D, String sala4D, String imax) {
        this.tradicional = tradicional;
        this.vip = vip;
        this.sala3D = sala3D;
        this.sala4D = sala4D;
        this.imax = imax;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdTipoSala() {
        return idTipoSala;
    }

    private void setIdTipoSala(int idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public String getTradicional() {
        return tradicional;
    }

    public void setTradicional(String tradicional) {
        this.tradicional = tradicional;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getSala3D() {
        return sala3D;
    }

    public void setSala3D(String sala3D) {
        this.sala3D = sala3D;
    }

    public String getSala4D() {
        return sala4D;
    }

    public void setSala4D(String sala4D) {
        this.sala4D = sala4D;
    }

    public String getImax() {
        return imax;
    }

    public void setImax(String imax) {
        this.imax = imax;
    }
}