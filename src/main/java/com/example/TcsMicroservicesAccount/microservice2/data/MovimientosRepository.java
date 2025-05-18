package com.example.TcsMicroservicesAccount.microservice2.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
    
}
