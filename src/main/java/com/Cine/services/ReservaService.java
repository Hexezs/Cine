package com.Cine.services;

import com.Cine.models.Boleto;
import com.Cine.models.Cartelera;
import com.Cine.models.Reserva;
import com.Cine.models.Usuario;
import com.Cine.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservaService {
    private final ReservaRepository reservaRepository = new ReservaRepository();

    public List<Reserva> obtenerReservasPorFuncion(int idCartelera) {
        return reservaRepository.getReservasByCartelera(idCartelera);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.getAllReservas();
    }

    public void cancelarReserva(Reserva reserva) {
        reservaRepository.removeReserva(reserva);
    }
    public Reserva procesarCompra(Usuario usuario, Cartelera cartelera, List<Boleto> boletos){

        if (usuario == null) throw new RuntimeException("Inicia sesión");
        if (cartelera == null) throw new RuntimeException("No función");
        if (boletos == null || boletos.isEmpty()) throw new RuntimeException("No boletos");

        Reserva reserva = new Reserva();
        reserva.setFecha(LocalDate.now());
        reserva.setIdusuario(usuario);
        reserva.setIdcartelera(cartelera);
        for (Boleto b : boletos) {

            b.setIdReserva(reserva);
        }

        reserva.setBoletos(boletos);

        reservaRepository.addReserva(reserva);

        return reserva;
    }
    public Reserva crearReserva(Usuario usuario, Cartelera cartelera){

        if(usuario == null)
            throw new RuntimeException("Inicia sesión");

        if(cartelera == null)
            throw new RuntimeException("No función");

        Reserva reserva = new Reserva();

        reserva.setFecha(LocalDate.now());
        reserva.setIdusuario(usuario);
        reserva.setIdcartelera(cartelera);

        reservaRepository.addReserva(reserva);

        return reserva;
    }

}
