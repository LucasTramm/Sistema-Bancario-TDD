/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banco;

/**
 *
 * @author lufre
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class BancoControle {

    private final Conta contaOrigem = new Conta("C1");
    private final Conta contaDestino = new Conta("C2");
    private final TransacaoService transacaoService = new TransacaoService();

    public BancoControle() {
        contaOrigem.depositar(1000.0);
    }

    private Map<String, Object> saldos() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("saldoOrigem", contaOrigem.getSaldo());
        resp.put("saldoDestino", contaDestino.getSaldo());
        return resp;
    }

    @GetMapping("/saldos")
    public ResponseEntity<?> getSaldos() {
        return ResponseEntity.ok(saldos());
    }

    @PostMapping("/depositar")
    public ResponseEntity<?> depositar(@RequestBody Map<String, Object> body) {
        double valor = ((Number) body.get("valor")).doubleValue();
        contaOrigem.depositar(valor);
        return ResponseEntity.ok(saldos());
    }

    @PostMapping("/sacar")
    public ResponseEntity<?> sacar(@RequestBody Map<String, Object> body) {
        double valor = ((Number) body.get("valor")).doubleValue();
        contaOrigem.sacar(valor);
        return ResponseEntity.ok(saldos());
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody Map<String, Object> body) {
        double valor = ((Number) body.get("valor")).doubleValue();
        transacaoService.transferir(contaOrigem, contaDestino, valor);
        return ResponseEntity.ok(saldos());
    }
}