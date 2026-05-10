package com.Cine.utils;
//tomamos logica <SharedData> o <Singleton>
import com.Cine.dto.ReservaRegistroDTO;
import com.Cine.dto.UsuarioDTO;
public class SesionUsuario {
    //instancia unica
    private static SesionUsuario instancia = null;

    //dato compartido
    private UsuarioDTO usuarioLog;
    private ReservaRegistroDTO enProceso; //reserva en proceso

    //constructor
    private SesionUsuario(){
        this.usuarioLog = null; //nadie inicialmente
        this.enProceso = null;
    }

    //acceso global
    public static SesionUsuario getInstance(){
        if(instancia == null){
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    //usamos "planos" del UsuarioDTO
    public UsuarioDTO getUsuarioLog(){
        return usuarioLog;
    }
    public void setUsuarioLog(UsuarioDTO usuarioLog){
        this.usuarioLog = usuarioLog;
    }
    public void cerrarSesion(){
        this.usuarioLog = null;
    }

    public ReservaRegistroDTO getReservaEnProceso(){
        return enProceso;
    }
    public void setReservaEnProceso(ReservaRegistroDTO reserva){
        this.enProceso = reserva;
    }
}
