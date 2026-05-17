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

    public Reserva procesarCompra(Usuario usuario, Cartelera cartelera, List<Boleto> boletos){
        if (usuario == null) {
            throw new RuntimeException("Inicia sesión para reservar");
        }
        if (cartelera == null) {
            throw new RuntimeException("No se seleccionó una función");
        }
        if (boletos == null) {
            throw new RuntimeException("No se seleccionaron asientos");
        }

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setFecha(LocalDate.now());
        nuevaReserva.setIdusuario(usuario);
        nuevaReserva.setIdcartelera(cartelera);

        for (Boleto b : boletos) {
            b.setIdReserva(nuevaReserva);
        }
        nuevaReserva.setBoletos(boletos);

        reservaRepository.addReserva(nuevaReserva);
        return nuevaReserva;
    }

    public List<Reserva> obtenerReservasPorFuncion(int idCartelera) {
        return reservaRepository.getReservasByCartelera(idCartelera);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.getAllReservas();
    }

    public void cancelarReserva(Reserva reserva) {
        reservaRepository.removeReserva(reserva);
    }
}
