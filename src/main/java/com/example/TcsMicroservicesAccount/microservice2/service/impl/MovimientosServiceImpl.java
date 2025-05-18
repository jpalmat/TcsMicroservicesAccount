package com.example.TcsMicroservicesAccount.microservice2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.data.CuentaRepository;
import com.example.TcsMicroservicesAccount.microservice2.data.Movimientos;
import com.example.TcsMicroservicesAccount.microservice2.data.MovimientosRepository;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoAccountException;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoBalanceException;
import com.example.TcsMicroservicesAccount.microservice2.service.MovimientosService;

@Service
public class MovimientosServiceImpl implements MovimientosService {
    MovimientosRepository movimientosRepository;
    CuentaRepository cuentaRepository;

    public MovimientosServiceImpl(MovimientosRepository movimientosRepository, CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientosRepository = movimientosRepository;
    }


    int count = 0;
    @Override
    public List<Movimientos> getAllMovimientos() {
        return movimientosRepository.findAll();
    }

    @Override
    public void addMovimiento(Long accountNumber, Movimientos movimientos) {
        Cuenta cuenta = cuentaRepository.findById(accountNumber).orElse(null);
        if (cuenta != null) {
            double balanceActual = cuenta.getBalance();
            double nuevoSaldo = balanceActual + movimientos.getAmount();

            if(nuevoSaldo < 0) {
                throw new NoBalanceException("Insufficient funds");
            }
            
            cuenta.setBalance(nuevoSaldo);
            movimientosRepository.save(movimientos);
            cuenta.getMovimientos().add(movimientos);
            cuentaRepository.save(cuenta);
        } else {
            throw new NoAccountException("Account " + accountNumber +"not found");
        }
    }

    @Override
    public boolean deleteMovimiento(Long id) {
        try {
        movimientosRepository.deleteById(id);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateMovimiento(Long id, Movimientos movimientos_updated) {

        Optional<Movimientos> companiesOptional = movimientosRepository.findById(id);
        if(companiesOptional.isPresent()) {
            Movimientos movimientos = companiesOptional.get();
            // personas.setName(persona_updated.getName());

            movimientosRepository.save(movimientos);
            return true;
        }
        return false;
    } 
}
