package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TipoUsuario")

public class TipoUsuario {

    private int idTipoUsuario;
    private String nombreTipoUsuario;

    public TipoUsuario(){}

    public TipoUsuario(String nombreTipoUsuario){
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdTipoUsuario(){
        return idTipoUsuario;
    }
    private void setIdTipoUsuario(int idTipoUsuario){
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario(){
        return nombreTipoUsuario;
    }
    public void setNombreTipoUsuario(String nombreTipoUsuario){
        this.nombreTipoUsuario = nombreTipoUsuario;
    }
}
