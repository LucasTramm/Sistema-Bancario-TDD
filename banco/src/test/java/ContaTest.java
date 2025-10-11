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
}
