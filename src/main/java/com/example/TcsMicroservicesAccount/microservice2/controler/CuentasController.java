package com.example.TcsMicroservicesAccount.microservice2.controler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TcsMicroservicesAccount.microservice2.dto.CuentaDTO;
import com.example.TcsMicroservicesAccount.microservice2.dto.ReportDTO;
import com.example.TcsMicroservicesAccount.microservice2.service.CuentaService;

/**
 * F1: Generación de CRUDS (Crear, editar, actualizar y eliminar registros - Entidades: Cliente,
Cuenta y Movimiento).
Los nombres de los endpoints a generar son: /cuentas
 */
@RestController
@RequestMapping("/cuentas")
public class CuentasController {

    private CuentaService cuentaService;

    public CuentasController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getCuenta() {
        return new ResponseEntity<>(this.cuentaService.getAllCuentas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCuenta(@RequestBody CuentaDTO cuenta) {
        this.cuentaService.addCuenta(cuenta);
        return new ResponseEntity<>("Cuenta added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCuenta(@PathVariable Long id) {
        this.cuentaService.deleteCuenta(id);
        return new ResponseEntity<>("Cuenta deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaUpdated) {
        this.cuentaService.updateCuenta(id, cuentaUpdated);
        return new ResponseEntity<>("Cuenta updated successfully", HttpStatus.OK);
    }

    @GetMapping("/cliente/{clientId}/report")
    public ResponseEntity<ReportDTO> report(@PathVariable Long clientId, 
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTransactionStart, 
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTransactionEnd) {
        return ResponseEntity.ok().body(cuentaService.getAllByAccountClientIdAndDateBetween(clientId, dateTransactionStart, dateTransactionEnd));
	}
}
