package com.Cine.services;

import com.Cine.dto.ReservaRegistroDTO;
import com.Cine.dto.UsuarioDTO;
import com.Cine.mapper.ReservaMapper;
import com.Cine.models.Cartelera;
import com.Cine.models.Reserva;
import com.Cine.models.Usuario;
import com.Cine.repository.CarteleraRepository;
import com.Cine.repository.ReservaRepository;
import com.Cine.repository.UsuarioRepository;
import com.Cine.utils.SesionUsuario;

public class ReservaService {
    private ReservaRepository reservaBD = new ReservaRepository();
    private UsuarioRepository usuarioBD = new UsuarioRepository();
    private CarteleraRepository carteleraBD = new CarteleraRepository();

    public synchronized boolean procesarCompra(ReservaRegistroDTO reservaDTO) throws Exception {
        //quien?
        UsuarioDTO usuarioSesion = SesionUsuario.getInstance().getUsuarioLog();

        if (usuarioSesion == null) {
            throw new Exception("Inicia sesion para reservar");
        }

        if (reservaDTO.idAsientoCompra() == null || reservaDTO.idAsientoCompra().isEmpty()) {
            throw new Exception("No se ha seleccionado un asiento");
        }

        Usuario usuarioEntity = usuarioBD.getUserByID(usuarioSesion.idUsuario());
        Cartelera carteleraEntity = carteleraBD.getCarteleraByID(reservaDTO.idCartelera());

        if(usuarioEntity == null || carteleraEntity == null){
            throw new Exception("GG");
        }

        //traductor
        Reserva nuevaReserva = ReservaMapper.aEntidad(reservaDTO,usuarioEntity,carteleraEntity);

        //guardar
        reservaBD.addReserva(nuevaReserva);

        //compra exitosa :D
        return true;
    }

}
