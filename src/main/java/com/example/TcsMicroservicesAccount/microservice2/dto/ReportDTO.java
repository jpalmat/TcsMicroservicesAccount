package com.example.TcsMicroservicesAccount.microservice2.dto;

import java.util.List;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;

public class ReportDTO {
    private String clienteNombre;
    private List<Cuenta> cuentasDeCliente;

    public ReportDTO(String clienteNombre, List<Cuenta> cuentasDeCliente) {
        this.clienteNombre = clienteNombre;
        this.cuentasDeCliente = cuentasDeCliente;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public List<Cuenta> getCuentasCliente() {
        return cuentasDeCliente;
    }

    public void setCuentasCliente(List<Cuenta> cuentasDeCliente) {
        this.cuentasDeCliente = cuentasDeCliente;
    }
}
