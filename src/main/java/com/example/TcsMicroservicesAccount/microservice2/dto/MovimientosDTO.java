package com.example.TcsMicroservicesAccount.microservice2.dto;

import java.time.LocalDate;

public class MovimientosDTO {
    private LocalDate fechaMovimiento;
    private String tipoMovimiento;
    private double monto;
    private double saldo;

    public MovimientosDTO() {
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
