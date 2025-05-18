package com.example.TcsMicroservicesAccount.microservice2.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta findByAccountNumber(Long numeroCuenta);
    
}
