package com.example.TcsMicroservicesAccount.microservice2.util;

import java.util.Date;

import com.example.TcsMicroservicesAccount.microservice2.data.Movimientos;
import com.example.TcsMicroservicesAccount.microservice2.dto.MovimientosDTO;

public class MovimientoMapperUtil {
    
    public static MovimientosDTO toDTO(Movimientos movimientos) {
        MovimientosDTO dto = new MovimientosDTO();
        dto.setFechaMovimiento(movimientos.getDate());
        dto.setTipoMovimiento(movimientos.getMovementType());
        dto.setMonto(movimientos.getAmount());
        dto.setSaldo(movimientos.getBalance());
        return dto;
    }

    public static Movimientos toEntity(MovimientosDTO movimientosDTO) {
        Movimientos movimientos = new Movimientos();
        movimientos.setDate(new Date());
        movimientos.setMovementType(movimientosDTO.getTipoMovimiento());
        movimientos.setAmount(movimientosDTO.getMonto());
        return movimientos;
    }
}
