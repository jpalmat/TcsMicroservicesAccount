package com.example.TcsMicroservicesAccount.microservice2.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.data.CuentaRepository;
import com.example.TcsMicroservicesAccount.microservice2.dto.ClienteDTO;
import com.example.TcsMicroservicesAccount.microservice2.dto.ReportDTO;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoAccountException;
import com.example.TcsMicroservicesAccount.microservice2.exceptions.NoClienteException;
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

    // @Override
    // public Cuenta getCuentaById(Long numeroCuenta) {
    //     return cuentaRepository.findByAccountNumber(numeroCuenta);
    // }

    // @Override
    // public List<Cuenta> getCuentasByClientId(Long clientId) {

    //     return cuentaRepository.findAll().stream()
    //             .filter(cuenta -> cuenta.getClientId().equals(clientId))
    //             .toList();
    // }
    
    @Override
    public ReportDTO getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart, Date dateTransactionEnd) {
// Report
        WebClient webClient = WebClient.create("http://localhost:8080/clientes");
        ClienteDTO client = webClient.get()
        .uri("/"+clientId)
        .retrieve()
        .bodyToMono(ClienteDTO.class)
        .block();
        if (client == null) {
            throw new NoClienteException("Client with id " + clientId + "not found");
        }

        List<Cuenta> cuentasDeClientes = cuentaRepository.findAll().stream()
                .filter(cuenta -> cuenta.getClientId().equals(clientId))
                .filter(cuenta -> cuenta.getMovimientos().stream()
                        .anyMatch(movimiento -> movimiento.getDate().after(dateTransactionStart) && movimiento.getDate().before(dateTransactionEnd)))
                .toList();

        if (cuentasDeClientes.isEmpty()) {
            throw new NoAccountException("Client with id " + clientId + "has no accounts");
        }

        return new ReportDTO(client.getName(), cuentasDeClientes);
    } 
}
