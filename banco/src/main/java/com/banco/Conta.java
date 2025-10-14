package com.banco;

public class Conta {
   private String numero;
    private double saldo;

    public Conta(String numero) {
        this.numero = numero;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        this.saldo += valor;
    }
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente."); 
        }
        this.saldo -= valor;
    }
    
    // Métodos getters
    public double getSaldo() {
        return saldo;
    }
    public String getNumero() {
        return numero;
    }
}