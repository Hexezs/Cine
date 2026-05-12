package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tipoUsuario")

public class TipoUsuario {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int idTipoUsuario;
    private String nombreTipoUsuario;

    public TipoUsuario(){}

    public TipoUsuario(String nombreTipoUsuario){
        this.nombreTipoUsuario = nombreTipoUsuario;
    }
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
