package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "tipousuario" )
public class tipousuario {
    private int idtipoUsuario;
    private byte esAdmin;

    public tipousuario(){
    }
    public tipousuario(byte esAdmin){
        this.esAdmin=esAdmin;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getIdtipoUsuario(){return idtipoUsuario;}

    public void setIdtipoUsuario(int idtipoUsuario){
        this.idtipoUsuario=idtipoUsuario;}

    public int getEsAdmin(){ return esAdmin;}

    public void setEsAdmin(byte esAdmin){this.esAdmin=esAdmin;}

}
