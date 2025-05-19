package com.example.TcsMicroservicesAccount.microservice2.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.data.CuentaRepository;
import com.example.TcsMicroservicesAccount.microservice2.dto.ClienteDTO;
import com.example.TcsMicroservicesAccount.microservice2.dto.CuentaDTO;
import com.example.TcsMicroservicesAccount.microservice2.dto.ReportDTO;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoAccountException;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoClienteException;
import com.example.TcsMicroservicesAccount.microservice2.service.CuentaService;
import com.example.TcsMicroservicesAccount.microservice2.util.CuentaMapperUtil;
import com.example.TcsMicroservicesAccount.microservice2.util.MovimientoMapperUtil;

@Service
public class CuentaServiceImpl implements CuentaService {
    CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }


    int count = 0;
    @Override
    public List<CuentaDTO> getAllCuentas() {
        return cuentaRepository.findAll().stream()
        .map(cuenta -> CuentaMapperUtil.toDTO(cuenta))
        .toList();
    }

    @Override
    public void addCuenta(CuentaDTO cuenta) {
        cuentaRepository.save(CuentaMapperUtil.toEntity(cuenta));
    }

    @Override
    public void deleteCuenta(Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if(cuentaOptional.isEmpty()) {
            throw new NoAccountException("Account with id " + id + " not found");
        }
        try {
            cuentaRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoAccountException("Unable to delete Account with id " + id);
        }
    }

    @Override
    public void updateCuenta(Long id, CuentaDTO cuenta_updated) {

        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if(cuentaOptional.isEmpty()) {
            throw new NoAccountException("Account with id " + id + " not found");
        }
        cuentaRepository.save(CuentaMapperUtil.toEntity(cuenta_updated));
    }
    
    /**
     F4: Reportes: Generar un reporte de “Estado de cuenta” especificando un rango de fechas y
    cliente. Este reporte debe contener:
        o Cuentas asociadas con sus respectivos saldos
        o Detalle de movimientos de las cuentas
     */
    @Override
    public ReportDTO getAllByAccountClientIdAndDateBetween(Long clientId, LocalDate dateTransactionStart, LocalDate dateTransactionEnd) {

        WebClient webClient = WebClient.create("http://localhost:8080/clientes");
        ClienteDTO client = webClient.get()
        .uri("/"+clientId)
        .retrieve()
        .bodyToMono(ClienteDTO.class)
        .block();
        if (client == null) {
            throw new NoClienteException("Client with id " + clientId + "not found");
        }
       
        List<Cuenta> cuentasUsuario = cuentaRepository.findAll().stream()
        .filter(cuenta -> cuenta.getClientId().equals(clientId)).collect(Collectors.toList());

        List<CuentaDTO> reporteUsuario = cuentasUsuario.stream().map(cuenta -> {

            CuentaDTO cuentaDTO = CuentaMapperUtil.toDTO(cuenta);
            cuentaDTO.setMovimientos(
            cuenta.getMovimientos().stream()
                .filter(movimiento -> movimiento.getDate().isAfter(dateTransactionStart) && movimiento.getDate().isBefore(dateTransactionEnd))
                .map(MovimientoMapperUtil::toDTO)
                .collect(Collectors.toList())
            );

            return cuentaDTO;
        }).collect(Collectors.toList());

        if (reporteUsuario.isEmpty()) {
            throw new NoAccountException("Client with id " + clientId + " has no accounts");
        }

        return new ReportDTO(client.getNombre(), reporteUsuario);
    } 
}
