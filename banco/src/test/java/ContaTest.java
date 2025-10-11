import com.banco.Conta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    @Test
    public void SaldoInicialZeroParaNovoConta() {
        Conta conta = new Conta("12345");
        double saldo = conta.getSaldo();
        assertEquals(0.0, saldo, "O saldo inicial deve ser zero.");      
    }
    @Test
    void DepositarValorPositivoEAtualizarSaldo() { // Teste 2
        Conta conta = new Conta("123");
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo());
    }
    
    @Test
    void naoDevePermitirDepositoDeValorNegativo() { // Teste 3
        Conta conta = new Conta("123");
        assertThrows(IllegalArgumentException.class, () -> {
            conta.depositar(-50.0);
        });
        assertEquals(0.0, conta.getSaldo());
    }
    
    @Test
    void SacarValorSeHouverSaldoSuficiente() { // Teste 4
        Conta conta = new Conta("123");
        conta.depositar(200.0);
        conta.sacar(50.0);
        assertEquals(150.0, conta.getSaldo());
    }
    
    @Test
    void LancarExcecaoAoTentarSacarValorNegativo() { // Teste 5
        Conta conta = new Conta("123");
        assertThrows(IllegalArgumentException.class, () -> {
            conta.sacar(-10.0);
        });
        assertEquals(0.0, conta.getSaldo());
    }

    @Test
    void naoDevePermitirSaqueComSaldoInsuficiente() { // Teste 6
        Conta conta = new Conta("123");
        conta.depositar(100.0);
        
        assertThrows(IllegalStateException.class, () -> {
            conta.sacar(150.0);
        });
        assertEquals(100.0, conta.getSaldo()); 
    }
    
    @Test
    void DepositarESacarComMultiplasOperacoes() { // Teste 7
        Conta conta = new Conta("999");
        conta.depositar(500.0);
        conta.sacar(100.0);
        conta.depositar(20.0);
        assertEquals(420.0, conta.getSaldo());
    }
    
    @Test
     public void naoDevePermitirDepositoDeValorZero() {//Teste 8
        Conta conta = new Conta("C08");
        assertThrows(IllegalArgumentException.class, () -> {
            conta.depositar(0.0);
        }, "Lançar exceção ao tentar depositar valor zero.");
        assertEquals(0.0, conta.getSaldo());
    }
    @Test
    public void naoDevePermitirSaqueDeValorZero() {//Teste 9
        Conta conta = new Conta("C09");
        conta.depositar(100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            conta.sacar(0.0);
        }, "Lançar exceção ao tentar sacar valor zero.");
        assertEquals(100.0, conta.getSaldo());
    }
    
    @Test
    public void SaqueDoValorExatoDoSaldoDeixandoContaZerada() {//Teste 10
        Conta conta = new Conta("C10");
        double valorExato = 345.67;
        conta.depositar(valorExato);
        conta.sacar(valorExato);
        assertEquals(0.0, conta.getSaldo(), 0.001, "O saldo deve ser zero após o saque exato.");
    }
}
