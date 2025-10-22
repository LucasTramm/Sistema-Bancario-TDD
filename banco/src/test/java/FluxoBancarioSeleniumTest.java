/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lufre
 */
import com.banco.Conta;
import com.banco.TransacaoService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Locale;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FluxoBancarioSeleniumTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static Conta contaOrigem = new Conta("C1");
    private static Conta contaDestino = new Conta("C2");
    private static TransacaoService transacaoService = new TransacaoService();

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        contaOrigem.depositar(1000.0); // saldo inicial

        String htmlFilePath = Paths.get("index.html").toUri().toString();
        driver.get(htmlFilePath);
        driver.manage().window().maximize();
    }

    private void log(String message) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "document.getElementById('test-log').innerHTML += '<p>> ' + arguments[0] + '</p>'; window.scrollTo(0, document.body.scrollHeight);",
            message
        );
    }

    private void atualizarSaldosUI() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('saldo-origem').innerText = arguments[0];",
                String.format(Locale.US, "%.2f", contaOrigem.getSaldo()));
        js.executeScript("document.getElementById('saldo-destino').innerText = arguments[0];",
                String.format(Locale.US, "%.2f", contaDestino.getSaldo()));
    }

    private void digitarComoHumano(WebElement element, String texto) {
        for (char c : texto.toCharArray()) {
            element.sendKeys(String.valueOf(c));

            // pausa entre 400ms e 800ms para simular digitação humana
            try {
                Thread.sleep(400 + (int) (Math.random() * 400));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // pequena pausa após digitar tudo
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void deveDepositarValorComSucesso() {
        log("Iniciando Teste 1: Depósito");

        WebElement inputDeposito = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("valor-deposito")));
        WebElement botaoDepositar = driver.findElement(By.id("btn-depositar"));

        double valorDeposito = 200.0;
        inputDeposito.click();
        digitarComoHumano(inputDeposito, String.valueOf(valorDeposito)); // digitação lenta
        botaoDepositar.click();

        contaOrigem.depositar(valorDeposito);
        atualizarSaldosUI();

        log("Depósito de R$ " + valorDeposito + " realizado.");

        WebElement saldoOrigemEl = driver.findElement(By.id("saldo-origem"));
        assertEquals("1200.00", saldoOrigemEl.getText());
        log("✅ Saldo atualizado corretamente na UI.");
    }

    @Test
    @Order(2)
    void deveSacarValorComSucesso() {
        log("Iniciando Teste 2: Saque");

        WebElement inputSaque = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("valor-saque")));
        WebElement botaoSacar = driver.findElement(By.id("btn-sacar"));

        double valorSaque = 150.0;
        inputSaque.click();
        digitarComoHumano(inputSaque, String.valueOf(valorSaque));
        botaoSacar.click();

        contaOrigem.sacar(valorSaque);
        atualizarSaldosUI();

        log("Saque de R$ " + valorSaque + " realizado.");

        WebElement saldoOrigemEl = driver.findElement(By.id("saldo-origem"));
        assertEquals("1050.00", saldoOrigemEl.getText());
        log("✅ Saldo atualizado corretamente após saque.");
    }

    @Test
    @Order(3)
    void deveTransferirValorComSucesso() {
        log("Iniciando Teste 3: Transferência");

        WebElement inputTransferencia = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("valor-transferencia")));
        WebElement botaoTransferir = driver.findElement(By.id("btn-transferir"));

        double valorTransferencia = 350.0;
        inputTransferencia.click();
        digitarComoHumano(inputTransferencia, String.valueOf(valorTransferencia));
        botaoTransferir.click();

        transacaoService.transferir(contaOrigem, contaDestino, valorTransferencia);
        atualizarSaldosUI();

        log("Transferência de R$ " + valorTransferencia + " realizada.");

        WebElement saldoOrigemEl = driver.findElement(By.id("saldo-origem"));
        WebElement saldoDestinoEl = driver.findElement(By.id("saldo-destino"));

        assertEquals("700.00", saldoOrigemEl.getText());
        assertEquals("350.00", saldoDestinoEl.getText());
        log("✅ Saldos atualizados corretamente após transferência.");
    }

     static void tearDownAll() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}