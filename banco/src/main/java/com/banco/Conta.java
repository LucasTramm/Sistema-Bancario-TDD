package com.banco;

public class Conta {
    private String numero;
    private double saldo;

    public Conta(String numero) {
        this.numero = numero;
        this.saldo = 0.0;
    }

    public double getSaldo() {
            return saldo;
        }
    }