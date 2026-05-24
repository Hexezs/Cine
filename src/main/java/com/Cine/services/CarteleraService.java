// ==========================
// SERVICE
// ==========================

package com.Cine.services;

import com.Cine.dto.CarteleraRegistroDTO;
import com.Cine.mapper.CarteleraMapper;
import com.Cine.models.Cartelera;
import com.Cine.models.Pelicula;
import com.Cine.models.Sala;
import com.Cine.repository.CarteleraRepository;
import com.Cine.repository.PeliculaRepository;
import com.Cine.repository.SalaRepository;

import java.time.LocalDate;
import java.util.List;

public class CarteleraService {

    private CarteleraRepository carteleraRepository = new CarteleraRepository();

    private PeliculaRepository peliculaRepository = new PeliculaRepository();

    private SalaRepository salaRepository = new SalaRepository();

    public void guardarFuncion(CarteleraRegistroDTO dto){
        Pelicula peliEncontrada = peliculaRepository.getPeliculaByID(dto.idPelicula());

        Sala salaEncontrada = salaRepository.getSalaByID(dto.idSala());

        if(peliEncontrada == null || salaEncontrada == null){
            return;
        }
        Cartelera nuevaFuncion = CarteleraMapper.aEntidad(dto, peliEncontrada, salaEncontrada);
        carteleraRepository.addCartelera(nuevaFuncion);
    }
    public boolean validarDuracionFuncion(Sala sala, LocalDate fecha, String horaInicio, int duracionPelicula) {
        List<Cartelera> funciones = carteleraRepository.getFuncionesPorSalaYFecha(sala.getIdsala(), fecha);

        int nuevaHora = Integer.parseInt(horaInicio.split(":")[0]) * 60 + Integer.parseInt(horaInicio.split(":")[1]);
        int nuevaHoraFin = nuevaHora + duracionPelicula;

        for (Cartelera f : funciones) {
            int existenteHora = Integer.parseInt(f.getHora().split(":")[0]) * 60 + Integer.parseInt(f.getHora().split(":")[1]);
            int existenteHoraFin = existenteHora + f.getIdpelicula().getTiempo();

            if (nuevaHora < existenteHoraFin && nuevaHoraFin > existenteHora) {
                return false; // hay conflicto
            }
        }
        return true; // sin conflicto
    }
}