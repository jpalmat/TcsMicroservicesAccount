package com.example.TcsMicroservicesAccount.microservice2.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.data.CuentaRepository;
import com.example.TcsMicroservicesAccount.microservice2.data.Movimientos;
import com.example.TcsMicroservicesAccount.microservice2.data.MovimientosRepository;
import com.example.TcsMicroservicesAccount.microservice2.dto.MovimientosDTO;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoAccountException;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoBalanceException;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoMovementException;
import com.example.TcsMicroservicesAccount.microservice2.service.MovimientosService;
import com.example.TcsMicroservicesAccount.microservice2.util.MovimientoMapperUtil;

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
    public List<MovimientosDTO> getAllMovimientos() {
        return movimientosRepository.findAll().stream()
        .map(movimiento -> MovimientoMapperUtil.toDTO(movimiento))
        .toList();
    }

    /**
     F2: Registro de movimientos: al registrar un movimiento en la cuenta se debe tener en cuenta
    lo siguiente:
        • Para un movimiento se pueden tener valores positivos o negativos.
        • Al realizar un movimiento se debe actualizar el saldo disponible.
        • Se debe llevar el registro de las transacciones realizadas
     */
    @Override
    public void addMovimiento(Long accountNumber, MovimientosDTO movimientosDTO) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(accountNumber);

        if(cuentaOptional.isEmpty()) {
            throw new NoAccountException("Account " + accountNumber +" not found");
        }

        Cuenta cuenta = cuentaOptional.get();
        double currentBalance = getCurrentBalance(cuenta);

        double newBalance = currentBalance + movimientosDTO.getMonto();

        /**
         F3: Registro de movimientos: Al realizar un movimiento el cual no cuente con saldo, debe
        alertar mediante el siguiente mensaje “Saldo no disponible”
            • Defina, según su expertise, la mejor manera de capturar y mostrar el error.
         */
        if(newBalance < 0) {
            throw new NoBalanceException("Insufficient funds in account " + accountNumber);
        }

        Movimientos movimiento = MovimientoMapperUtil.toEntity(movimientosDTO);
        movimiento.setBalance(newBalance);
        
        movimientosRepository.save(movimiento);
        cuenta.getMovimientos().add(movimiento);
        cuentaRepository.save(cuenta);
    }

    private double getCurrentBalance(Cuenta cuenta) {
        if(cuenta.getMovimientos().size() == 0) {
            return cuenta.getBalance();
        } else {
            return cuenta.getMovimientos().get(cuenta.getMovimientos().size() - 1).getBalance();
        }
    }

    @Override
    public void deleteMovimiento(Long id) {
        Optional<Movimientos> movimientoOptional = movimientosRepository.findById(id);
        if(movimientoOptional.isEmpty()) {
            throw new NoMovementException("Movimiento with id " + id +" not found");
        }
        try {
            movimientosRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoMovementException("Unable to delete Movimiento with id " + id);
        }
    }

    @Override
    public void updateMovimiento(Long id, MovimientosDTO movimientos_updated) {

        Optional<Movimientos> movimientoOptional = movimientosRepository.findById(id);

        if(movimientoOptional.isEmpty()) {
            throw new NoMovementException("Movimiento with id " + id +" not found");
        }

        movimientosRepository.save(MovimientoMapperUtil.toEntity(movimientos_updated));

    }

}
