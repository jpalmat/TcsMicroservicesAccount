package com.example.TcsMicroservicesAccount.microservice2.data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cuenta {
    @Id
    private Long accountNumber;
    private String accountType;
    private double balance;
    private String state;
    private Long clientId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimientos> movimientos = new ArrayList<>();
    

    public Cuenta() {
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
   
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Movimientos> getMovimientos() {
        return movimientos;
    }
    public void setMovimientos(List<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }
}
