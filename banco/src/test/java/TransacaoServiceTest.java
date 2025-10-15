/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lufre
 */
import com.banco.TransacaoService;
import com.banco.Conta;
import com.banco.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransacaoServiceTest {
    private TransacaoService service;
    private Conta contaOrigem;
    private Conta contaDestino;

    @BeforeEach // Configuração antes de cada teste
    void setUp() {
        service = new TransacaoService();
        contaOrigem = new Conta("1000");
        contaDestino = new Conta("2000");
    }

    // Cenário 1: Sucesso
    
    @Test
    void deveTransferirValorComSucessoEntreDuasContas() { // Teste 1
        contaOrigem.depositar(300.0);
        service.transferir(contaOrigem, contaDestino, 100.0);

        assertEquals(200.0, contaOrigem.getSaldo());
        assertEquals(100.0, contaDestino.getSaldo());
    }
    
    @Test
    void deveTransferirValorMaximoComSucesso() { // Teste 2
        contaOrigem.depositar(500.0);
        service.transferir(contaOrigem, contaDestino, 500.0);

        assertEquals(0.0, contaOrigem.getSaldo());
        assertEquals(500.0, contaDestino.getSaldo());
    }

    @Test
    void deveTransferirValorPequenoComSucesso() { // Teste 3
        contaOrigem.depositar(1.0);
        service.transferir(contaOrigem, contaDestino, 0.50);

        assertEquals(0.50, contaOrigem.getSaldo(), 0.001);
        assertEquals(0.50, contaDestino.getSaldo(), 0.001);
    }

    // Cenário 2: Falha

    @Test
    void naoDeveTransferirComSaldoInsuficiente() { // Teste 4
        contaOrigem.depositar(100.0);
        
        // Verifica se a exceção é lançada
        assertThrows(SaldoInsuficienteException.class, () -> {
            service.transferir(contaOrigem, contaDestino, 150.0);
        });
        // Garante que a transação não foi completada
        assertEquals(100.0, contaOrigem.getSaldo(), "Saldo da origem deve ser mantido.");
        assertEquals(0.0, contaDestino.getSaldo(), "Saldo do destino deve ser zero.");
    }
    
    @Test
    void naoDeveTransferirComValorNegativo() { // Teste 5
        contaOrigem.depositar(100.0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.transferir(contaOrigem, contaDestino, -10.0);
        });

        assertEquals(100.0, contaOrigem.getSaldo(), "Saldo da origem deve ser mantido.");
        assertEquals(0.0, contaDestino.getSaldo(), "Saldo do destino deve ser zero.");
    }
}
