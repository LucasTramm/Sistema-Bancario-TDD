import com.banco.Conta;
import com.banco.SaldoInsuficienteException;
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
    void DepositarValorPositivoEAtualizarSaldo() {
        Conta conta = new Conta("123");
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void naoDevePermitirDepositoDeValorNegativo() {
        Conta conta = new Conta("123");
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(-50.0));
        assertEquals(0.0, conta.getSaldo());
    }

    @Test
    void naoDevePermitirDepositoDeValorZero() {
        Conta conta = new Conta("C08");
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(0.0));
        assertEquals(0.0, conta.getSaldo());
    }

    @Test
    void SacarValorSeHouverSaldoSuficiente() {
        Conta conta = new Conta("123");
        conta.depositar(200.0);
        conta.sacar(50.0);
        assertEquals(150.0, conta.getSaldo());
    }

    @Test
    void LancarExcecaoAoTentarSacarValorNegativo() {
        Conta conta = new Conta("123");
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(-10.0));
        assertEquals(0.0, conta.getSaldo());
    }

    @Test
    void naoDevePermitirSaqueDeValorZero() {
        Conta conta = new Conta("C09");
        conta.depositar(100.0);
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(0.0));
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void naoDevePermitirSaqueComSaldoInsuficiente() {
        Conta conta = new Conta("123");
        conta.depositar(100.0);
        assertThrows(SaldoInsuficienteException.class, () -> conta.sacar(150.0));
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void DepositarESacarComMultiplasOperacoes() {
        Conta conta = new Conta("999");
        conta.depositar(500.0);
        conta.sacar(100.0);
        conta.depositar(20.0);
        assertEquals(420.0, conta.getSaldo());
    }

    @Test
    public void SaqueDoValorExatoDoSaldoDeixandoContaZerada() {
        Conta conta = new Conta("C10");
        double valorExato = 345.67;
        conta.depositar(valorExato);
        conta.sacar(valorExato);
        assertEquals(0.0, conta.getSaldo(), 0.001);
    }
}