package com.example.TcsMicroservicesAccount.microservice2.dto;

import java.util.List;

public class ReportDTO {
    private String clienteNombre;
    private List<CuentaDTO> cuentasDeCliente;

    public ReportDTO(String clienteNombre, List<CuentaDTO> cuentasDeCliente) {
        this.clienteNombre = clienteNombre;
        this.cuentasDeCliente = cuentasDeCliente;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public List<CuentaDTO> getCuentasCliente() {
        return cuentasDeCliente;
    }

    public void setCuentasCliente(List<CuentaDTO> cuentasDeCliente) {
        this.cuentasDeCliente = cuentasDeCliente;
    }
}
