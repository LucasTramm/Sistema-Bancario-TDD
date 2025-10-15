/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco;

/**
 *
 * @author lufre
 */
public class TransacaoService {
    public void transferir(Conta origem, Conta destino, double valor) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("As contas de origem e destino não podem ser nulas.");
        }
        origem.sacar(valor);
        destino.depositar(valor);
    }
}