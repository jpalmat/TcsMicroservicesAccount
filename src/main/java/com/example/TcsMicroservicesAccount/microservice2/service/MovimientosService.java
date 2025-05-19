package com.example.TcsMicroservicesAccount.microservice2.service;

import java.util.List;

import com.example.TcsMicroservicesAccount.microservice2.dto.MovimientosDTO;

public interface MovimientosService {
    List<MovimientosDTO> getAllMovimientos();
    void addMovimiento(Long accountNumber, MovimientosDTO movimientos);
    void deleteMovimiento(Long id);
    void updateMovimiento(Long id, MovimientosDTO movimientos_updated);
}
