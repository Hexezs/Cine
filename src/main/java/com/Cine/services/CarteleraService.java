package com.Cine.services;

import com.Cine.dto.CarteleraDTO;
import com.Cine.dto.PeliculaRegistroDTO;
import com.Cine.mapper.CarteleraMapper;
import com.Cine.models.Cartelera;
import com.Cine.models.Pelicula;
import com.Cine.models.Sala;
import com.Cine.repository.CarteleraRepository;
import com.Cine.repository.PeliculaRepository;
import com.Cine.repository.SalaRepository;

public class CarteleraService {
    private CarteleraRepository carteleraRepository = new CarteleraRepository();
    private PeliculaRepository peliculaRepository = new PeliculaRepository();
    private SalaRepository salaRepository = new SalaRepository();

    public void guardarFuncion(CarteleraDTO dto){
        Cartelera nuevaFuncion = CarteleraMapper.aEntidad(dto);

        Pelicula peliEncontrada = peliculaRepository.getPeliculaByID((dto.idpelicula()));

        if(peliEncontrada != null){
            nuevaFuncion.setIdpelicula(peliEncontrada);
        }

        carteleraRepository.addCartelera(nuevaFuncion);
    }
}
