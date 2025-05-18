package com.example.TcsMicroservicesAccount.microservice2.service;

import java.util.List;
import com.example.TcsMicroservicesAccount.microservice2.data.Movimientos;

public interface MovimientosService {
    List<Movimientos> getAllMovimientos();
    void addMovimiento(Long accountNumber, Movimientos movimientos);
    boolean deleteMovimiento(Long id);
    boolean updateMovimiento(Long id, Movimientos movimientos_updated);
}
