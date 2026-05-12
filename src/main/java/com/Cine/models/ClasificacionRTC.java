package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ClasificacionRTC")
public class ClasificacionRTC {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idClasificacionRTC;
    private String nombre;
    private String descripcion;

    public ClasificacionRTC() {}

    public ClasificacionRTC(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public int getIdClasificacionRTC() {
        return idClasificacionRTC;
    }

    private void setIdClasificacionRTC(int idClasificacionRTC) {
        this.idClasificacionRTC = idClasificacionRTC;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}