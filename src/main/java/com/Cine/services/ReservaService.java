package com.Cine.services;

import com.Cine.dto.BoletoDTO;
import com.Cine.dto.ReservaRegistroDTO;

public class ReservaService {
    private ReservaRepository reservaRepository;
    private BoletoRepository boletoRepository;
    private SalaRepository salaRepository;

    public ReservaService{
        this.reservaRepository = new ReservaRepository();
        this.boletoRepository = new BoletoRepository();
        this.salaRepository = new SalaRepository();
    }

    public void crearReserva(ReservaRegistroDTO dto) throws Exception{
        if (sala == null) {
            throw new Exception("La sala no existe");
        }

        if(sala.getDisponibles() < dto.boletos().size()){
            throw new Exception("Error: No hay suficientes asientos disponibles en esta sala");
        }

        ReservaEntity reserva = new ReservaEntity();
        reserva.setFecha(dto.fecha()); //cambia
        reserva.setUsuario_idusuario(dto.idUsuario()); //cambia
        reserva.setSala_idsala(dto.idSala()); //cambia

        reservaRepository.save(reserva);

        for(BoletoDTO boletoDTO : dto.boletos()){
            BoletoEntity boleto = new BoletoEntity();
            boleto.setCantidad(boletoDTO.cantidad()); //cambio
            boleto.setMonto(boletoDTO.monto()); //cambio
            boleto.setAsiento(boletoDTO.asiento()); //cambio

            boleto.setReserva_idreserva(reserva.getIdreserva());

            boletoRepository.save(boleto);
        }

        int nuevosDisponibles = sala.getDisponibles() - dto.boletos().size();
        sala.setDisponibles(nuevosDisponibles);
        salaRepository.update(sala);

        System.out.println("Reserva completada excitosamente");
    }
}
