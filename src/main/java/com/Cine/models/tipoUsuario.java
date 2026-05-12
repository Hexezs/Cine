package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name ="tipoUsuario")
public class tipoUsuario {
    private int id;
    private String nombreTipoUsuario;

    public tipoUsuario(){
    }
    public tipoUsuario(String nombreTipoUsuario){
        this.nombreTipoUsuario=nombreTipoUsuario;
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId(){ return id;}
    void setId(int id){this.id=id;}

    public String getNombreTipoUsuario(){return nombreTipoUsuario;}
    public void setNombreTipoUsuario(String nombreTipoUsuario){this.nombreTipoUsuario=nombreTipoUsuario;}

}
