package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Idioma")
public class Idioma {

    private int idIdioma;
    private String nombreIdioma;

    public Idioma() {}

    public Idioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
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

    public String getNombreIdioma() {
        return nombreIdioma;
    }
    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }
}