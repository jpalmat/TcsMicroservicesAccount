package com.example.TcsMicroservicesAccount.microservice2.util;

import com.example.TcsMicroservicesAccount.microservice2.data.Cuenta;
import com.example.TcsMicroservicesAccount.microservice2.dto.CuentaDTO;

public class CuentaMapperUtil {
    
    public static CuentaDTO toDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNumeroCuenta(cuenta.getAccountNumber());
        cuentaDTO.setTipoCuenta(cuenta.getAccountType());
        cuentaDTO.setSaldo(cuenta.getBalance());
        cuentaDTO.setEstado(cuenta.getState());
        cuentaDTO.setClientId(cuenta.getClientId());
        cuentaDTO.setMovimientos(cuenta.getMovimientos().stream().map(movimiento -> MovimientoMapperUtil.toDTO(movimiento)).toList());
        return cuentaDTO;
    }

    public static Cuenta toEntity(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setAccountNumber(cuentaDTO.getNumeroCuenta());
        cuenta.setAccountType(cuentaDTO.getTipoCuenta());
        cuenta.setBalance(cuentaDTO.getSaldo());
        cuenta.setState(cuentaDTO.getEstado());
        cuenta.setClientId(cuentaDTO.getClientId());
        return cuenta;
    }
}
