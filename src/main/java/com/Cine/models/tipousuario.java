package com.Cine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tipoUsuario")
public class TipoUsuario {

    private int idTipoUsuario;
    private boolean esAdmin;
    public TipoUsuario() {}

    public TipoUsuario(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    private void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
}