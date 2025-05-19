package com.example.TcsMicroservicesAccount.microservice2.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TcsMicroservicesAccount.microservice2.dto.MovimientosDTO;
import com.example.TcsMicroservicesAccount.microservice2.service.MovimientosService;

/**
 * F1: Generación de CRUDS (Crear, editar, actualizar y eliminar registros - Entidades: Cliente,
Cuenta y Movimiento).
Los nombres de los endpoints a generar son: /movimientos
 */
@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    private MovimientosService movimientosService;

    public MovimientosController(MovimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }

    @GetMapping
    public ResponseEntity<List<MovimientosDTO>> getMovimientos() {
        return new ResponseEntity<>(this.movimientosService.getAllMovimientos(), HttpStatus.OK);
    }

    @PostMapping("/{accountNumber}")
    public ResponseEntity<String> addMovimiento(@PathVariable Long accountNumber, @RequestBody MovimientosDTO movimientos) {
        this.movimientosService.addMovimiento(accountNumber, movimientos);
        return new ResponseEntity<>("Movimiento added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovimiento(@PathVariable Long id) {
        this.movimientosService.deleteMovimiento(id);
        return new ResponseEntity<>("Movimiento deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovimiento(@PathVariable Long id, @RequestBody MovimientosDTO movimientosUpdated) {
        this.movimientosService.updateMovimiento(id, movimientosUpdated);
        return new ResponseEntity<>("Movimiento updated successfully", HttpStatus.OK);
    }
}
