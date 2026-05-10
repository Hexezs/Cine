package com.Cine.utils;

import com.Cine.dto.CarteleraDTO;

import java.util.List;

public class CompraReserva {
    private static CompraReserva instancia = null;

    private CarteleraDTO peliSelected;
    private List<Integer> asientosOcupados;

    private CompraReserva(){
        this.peliSelected = peliSelected;
        this.asientosOcupados = asientosOcupados;
    }

    public static CompraReserva getInstance(){
        if(instancia == null){
            instancia = new CompraReserva();
        }
        return instancia;
    }

    public void limpiarCompra(){
        this.peliSelected = null;
        this.asientosOcupados = null;
    }
}
