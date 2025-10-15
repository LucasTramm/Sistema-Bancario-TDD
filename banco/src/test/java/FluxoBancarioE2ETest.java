/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lufre
 */
import com.banco.SaldoInsuficienteException;
import com.banco.Conta;
import com.banco.TransacaoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FluxoBancarioE2ETest {

    @Test
    void fluxoDeVidaCompletoDaConta() { // Teste E2E 1
        // 1. Criação (Simula o início da sessão)
        Conta conta = new Conta("9999");
        assertEquals(0.0, conta.getSaldo());

        // 2. Depósito
        conta.depositar(500.0);
        assertEquals(500.0, conta.getSaldo());

        // 3. Saque com Sucesso
        conta.sacar(200.0);
        assertEquals(300.0, conta.getSaldo());

        // 4. Saque com Falha (e verificação da não alteração)
        assertThrows(SaldoInsuficienteException.class, () -> {
            conta.sacar(400.0); // Tenta sacar mais do que tem
        });
        
        // 5. Verificação final
        assertEquals(300.0, conta.getSaldo(), "O saldo deve permanecer após o saque falho.");
    }
    
    @Test
    void fluxoCompletoDeTransferenciaEntreClientes() { // Teste E2E 2
        // Arrange
        Conta c1 = new Conta("C1");
        Conta c2 = new Conta("C2");
        TransacaoService service = new TransacaoService();

        // 1. Preparação (Depósito inicial)
        c1.depositar(1000.0);
        assertEquals(1000.0, c1.getSaldo());
        assertEquals(0.0, c2.getSaldo());

        // 2. Transferência
        service.transferir(c1, c2, 300.0);

        // 3. Verificação (Confirmação da operação)
        assertEquals(700.0, c1.getSaldo());
        assertEquals(300.0, c2.getSaldo());

        // 4. Nova Operação (Saque de c2)
        c2.sacar(50.0);
        assertEquals(250.0, c2.getSaldo());
    }
}
