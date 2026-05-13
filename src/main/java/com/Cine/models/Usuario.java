package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "usuarios" )
public class Usuario {

    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String password;

    public Usuario() {

    }

    public Usuario(String nombre, String apellidoP, String apellidoM, String correo, String password, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.password = password;
        this.tipoUsuario=tipoUsuario;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int idusuario;
    public int getIdusuario() {
        return idusuario;
    }

    private void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }
    public String getCorreo() {
        return correo;
    }

    public void setPassword(String password) {this.password = password;}

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {return password;
    }
    @ManyToOne
    @JoinColumn(name ="tipoUsuario_idtipoUsuario", referencedColumnName = "idtipoUsuario", foreignKey = @ForeignKey (name = "idtipoUsuario"))
    private TipoUsuario tipoUsuario;
    public TipoUsuario getTipoUsuario(){return tipoUsuario;}
    public void setTipoUsuario(TipoUsuario tipoUsuario){this.tipoUsuario=tipoUsuario;}

}