package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ClasificacionRTC")
public class ClasificacionRTC {

    private int idClasificacionRTC;
    private String aa;
    private String a;
    private String b;
    private String b15;
    private String c;
    private String d;

    public ClasificacionRTC() {}

    public ClasificacionRTC(String aa, String a, String b, String b15, String c, String d) {
        this.aa = aa;
        this.a = a;
        this.b = b;
        this.b15 = b15;
        this.c = c;
        this.d = d;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdClasificacionRTC() {
        return idClasificacionRTC;
    }

    private void setIdClasificacionRTC(int idClasificacionRTC) {
        this.idClasificacionRTC = idClasificacionRTC;
    }
    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getA() {
        return a;
    }
    public void setA(String a) {
        this.a = a;
    }
    public String getB() {
        return b;
    }
    public void setB(String b) {
        this.b = b;
    }

    public String getB15() {
        return b15;
    }
    public void setB15(String b15) {
        this.b15 = b15;
    }

    public String getC() {
        return c;
    }
    public void setC(String c) {
        this.c = c;
    }
    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}