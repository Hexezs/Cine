package com.Cine.services;

import com.Cine.dto.CarteleraDTO;
import com.Cine.models.*;
import com.Cine.repository.AsientoRepository;
import com.Cine.repository.CarteleraRepository;
import com.Cine.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private final ReservaRepository reservaRepository = new ReservaRepository();
    private final AsientoRepository asientoRepository = new AsientoRepository();
    private final CarteleraRepository carteleraRepository = new CarteleraRepository();
    public List<Reserva> obtenerReservasPorFuncion(int idCartelera) {
        return reservaRepository.getReservasByCartelera(idCartelera);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.getAllReservas();
    }
    public void cancelarReserva(Reserva reserva) {
        reservaRepository.removeReserva(reserva);
    }

    //agregamos un candado
    public synchronized Reserva procesarCompra(
            Usuario usuario,
            CarteleraDTO carteleraDTO,
            List<String> asientosSeleccionados
    ) {

        if (usuario == null)
            throw new RuntimeException("Inicia sesión");

        if (carteleraDTO == null)
            throw new RuntimeException("No función");

        if (asientosSeleccionados == null || asientosSeleccionados.isEmpty())
            throw new RuntimeException("No boletos");
        Cartelera cartelera = carteleraRepository.getCarteleraByID(carteleraDTO.idCartelera());

        //inicio concurrencia
        List<Reserva> rservasActuales = obtenerReservasPorFuncion(cartelera.getIdCartelera());
        for(Reserva r : rservasActuales) {
            for(Boleto b : r.getBoletos()) {
                if(asientosSeleccionados.contains(b.getNombreasiento())) {
                    throw new RuntimeException("Asiento " + b.getNombreasiento() + " ya ocupado");
                }
            }
        }//fin concurrencia

        Reserva reserva = new Reserva();
        reserva.setFecha(LocalDate.now());
        reserva.setIdusuario(usuario);
        reserva.setIdcartelera(cartelera);

        List<Boleto> boletos = new ArrayList<>();

        for (String s : asientosSeleccionados) {

            String letra = s.substring(0, 1);
            String numero = s.substring(1);

            Asiento asiento = asientoRepository.buscarAsiento(
                    letra,
                    numero,
                    cartelera.getIdsala().getIdsala()
            );

            Boleto boleto = new Boleto();
            boleto.setNombreasiento(s);
            boleto.setCantidad(1);
            boleto.setMonto(120);
            boleto.setIdasiento(asiento);

            boleto.setIdReserva(reserva);

            boletos.add(boleto);
        }
        reserva.setBoletos(boletos);
        reservaRepository.addReserva(reserva);
        return reserva;
    }

    public Reserva crearReserva(Usuario usuario, Cartelera cartelera) {
        if (usuario == null)
            throw new RuntimeException("Inicia sesión");
        if (cartelera == null)
            throw new RuntimeException("No función");
        Reserva reserva = new Reserva();
        reserva.setFecha(LocalDate.now());
        reserva.setIdusuario(usuario);
        reserva.setIdcartelera(cartelera);
        reservaRepository.addReserva(reserva);
        return reserva;
    }
}