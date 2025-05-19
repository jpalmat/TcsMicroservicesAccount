package com.example.TcsMicroservicesAccount.microservice2.dto;

import java.util.ArrayList;
import java.util.List;

public class CuentaDTO {
    private Long numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private String estado;
    private Long clientId;
    private List<MovimientosDTO> movimientos = new ArrayList<>();

    public CuentaDTO() {
    }

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<MovimientosDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientosDTO> movimientos) {
        this.movimientos = movimientos;
    }
}
