package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Idioma")
public class Idioma {

    private int idIdioma;
    private String espanol;
    private String ingles;
    private String japones;
    private String ruso;

    public Idioma() {}

    public Idioma(String espanol, String ingles, String japones, String ruso) {
        this.espanol = espanol;
        this.ingles = ingles;
        this.japones = japones;
        this.ruso = ruso;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdIdioma() {
        return idIdioma;
    }

    private void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }
    public String getEspañol() {
        return espanol;
    }

    public void setEspañol(String español) {
        this.espanol = español;
    }

    public String getIngles() {
        return ingles;
    }
    public void setIngles(String ingles) {
        this.ingles = ingles;
    }
    public String getJapones() {
        return japones;
    }

    public void setJapones(String japones) {
        this.japones = japones;
    }
    public String getRuso() {
        return ruso;
    }
    public void setRuso(String ruso) {
        this.ruso = ruso;
    }
}