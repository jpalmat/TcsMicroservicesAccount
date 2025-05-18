package com.example.TcsMicroservicesAccount.microservice2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.data.CuentaRepository;
import com.example.TcsMicroservicesAccount.microservice2.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService {
    CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }


    int count = 0;
    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public void addCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    @Override
    public boolean deleteCuenta(Long id) {
        try {
        cuentaRepository.deleteById(id);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCuenta(Long id, Cuenta cuenta_updated) {

        Optional<Cuenta> companiesOptional = cuentaRepository.findById(id);
        if(companiesOptional.isPresent()) {
            Cuenta cuenta = companiesOptional.get();
            // personas.setName(persona_updated.getName());

            cuentaRepository.save(cuenta);
            return true;
        }
        return false;
    }

    @Override
    public Cuenta getCuentaById(Long numeroCuenta) {
        return cuentaRepository.findByAccountNumber(numeroCuenta);
    }

    @Override
    public List<Cuenta> getCuentasByClientId(Long clientId) {

        return cuentaRepository.findAll().stream()
                .filter(cuenta -> cuenta.getClientId().equals(clientId))
                .toList();
        }    
}
