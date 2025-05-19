MICROSERVICE 2

Cuenta - Movimiento

ENDPOINTS

CUENTAS
- getCuenta : GET /cuentas
- addCuenta : POST /cuentas body: CuentaDTO
- deleteCuenta : DELETE /cuentas/{id}
- updateCuentas : PUT /cuentas/{id}
- report : GET /cuentas/cliente/{clienteId}/report

MOVIMIENTOS
- getMovimientos : GET /movimientos
- addMovimiento : POST /movimientos body: MovimientosDTO
- deleteMovimiento : DELETE /movimientos/{id}
- updateMovimiento : PUT /movimientos/{id}
