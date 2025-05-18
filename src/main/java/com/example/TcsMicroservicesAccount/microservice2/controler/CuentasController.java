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

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentasController {

    private CuentaService cuentaService;

    public CuentasController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> getCuenta() {
        return new ResponseEntity<>(this.cuentaService.getAllCuentas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCuenta(@RequestBody Cuenta cuenta) {
        this.cuentaService.addCuenta(cuenta);
        return new ResponseEntity<>("Cuenta added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCuenta(@PathVariable Long id) {
        boolean deleted = this.cuentaService.deleteCuenta(id);
        if (!deleted) {
            return new ResponseEntity<>("Cuenta not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Cuenta deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaUpdated) {
        boolean updated = this.cuentaService.updateCuenta(id, cuentaUpdated);
        if (!updated) {
            return new ResponseEntity<>("Cuenta not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Cuenta updated successfully", HttpStatus.OK);
    }
}
