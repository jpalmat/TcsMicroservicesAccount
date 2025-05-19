package com.example.TcsMicroservicesAccount.microservice2.service;

import java.time.LocalDate;
import java.util.List;

import com.example.TcsMicroservicesAccount.microservice2.dto.CuentaDTO;
import com.example.TcsMicroservicesAccount.microservice2.dto.ReportDTO;

public interface CuentaService {
    List<CuentaDTO> getAllCuentas();
    void addCuenta(CuentaDTO cuenta);
    void deleteCuenta(Long id);
    void updateCuenta(Long id, CuentaDTO cuenta_updated);
    ReportDTO getAllByAccountClientIdAndDateBetween(Long clientId, LocalDate dateTransactionStart, LocalDate dateTransactionEnd);
}
