package com.example.TcsMicroservicesAccount.microservice2.service;

import java.util.List;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;

public interface CuentaService {
    List<Cuenta> getAllCuentas();
    Cuenta getCuentaById(Long id);
    List<Cuenta> getCuentasByClientId(Long clientId);
    void addCuenta(Cuenta cuenta);
    boolean deleteCuenta(Long id);
    boolean updateCuenta(Long id, Cuenta cuenta_updated);
}
