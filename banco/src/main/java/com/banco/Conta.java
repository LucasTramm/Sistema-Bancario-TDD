package com.banco;

public class Conta {
   private String numero;
    private double saldo;

    public Conta(String numero) {
        this.numero = numero;
        this.saldo = 0.0;
    }
    private void validarValorPositivo(double valor, String operacao) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da " + operacao + " deve ser positivo.");
        }
    }

    public void depositar(double valor) {
        validarValorPositivo(valor, "depósito");
        this.saldo += valor;
    }
    public void sacar(double valor) {
        validarValorPositivo(valor, "saque");
        if (valor > this.saldo){
        throw new IllegalStateException("Saldo insuficiente");
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