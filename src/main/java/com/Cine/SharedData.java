package com.Cine;

import com.Cine.models.Usuario;

public class SharedData {
    private static SharedData instance;
    private Usuario usuarioLogueado;
    private boolean modoEdicion = false;

    private SharedData() {}

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }
    public boolean existeUsuario() {
        return usuarioLogueado != null;
    }

    public void limpiarSesion() {
        this.usuarioLogueado = null;
        this.modoEdicion = false;
    }
    public Usuario getUsuarioLogueado() { return usuarioLogueado; }
    public void setUsuarioLogueado(Usuario u) { this.usuarioLogueado = u; }
    public boolean isModoEdicion() { return modoEdicion; }
    public void setModoEdicion(boolean m) { this.modoEdicion = m; }
}