# 🏦 **Projeto Banco — Sistema de Contas e Transações**

Este projeto em **Java** simula a lógica de negócio básica de um **sistema bancário**, com foco nas classes `Conta` e `TransacaoService`.  
O projeto utiliza:

- 🧱 **Apache Maven** como gerenciador de build  
- 🧪 **JUnit 5** para testes unitários  
- 📊 **JaCoCo** para verificação de cobertura de código  

---

## 🚀 **Requisitos**

Para executar e testar o projeto, você precisará ter instalado:

- ☕ **JDK (Java Development Kit)** — versão **17** ou superior  
- 🧱 **Apache Maven** — versão **3.x** ou superior  
- 💻 **IDE (opcional)** — *Apache NetBeans*, *IntelliJ IDEA* ou *Eclipse*

---

## ⚙️ **Configuração e Execução**

### 🌀 **1. Clonar o repositório**

No terminal, navegue até o diretório onde deseja salvar o projeto e execute:

```bash
git clone <URL_DO_REPOSITORIO>
cd banco
```
---
### 🧩 **2. Compilar o projeto**

Execute o comando Maven para baixar as dependências e compilar o código-fonte e os testes:

```bash
mvn clean compile
```
---
✅ **3. Executar testes e verificar cobertura (JaCoCo)**

O projeto está configurado para:

Executar todos os testes unitários

Gerar o relatório de cobertura de código

Falhar o build se a cobertura for inferior a 75%

Execute o ciclo completo com:

```bash
mvn clean verify
```
---
### **📈 Visualizar o relatório de cobertura (JaCoCo)**

Após o comando mvn clean verify, o relatório HTML será gerado em:

```bash
target/site/jacoco/index.html
```
🔍 Para visualizar:

1. Navegue até target/site/jacoco/

2. Abra index.html no navegador
---
## 💡 **Execução via IDE (NetBeans)**

🧭 Abrir o projeto

Vá em File → Open Project

Selecione o arquivo pom.xml

🧪 Executar testes e cobertura

Clique com o botão direito no projeto

Escolha Run Maven → Goals

Digite em Goals verify e clique em OK

📂 Acessar o relatório

Na aba Files, vá até:

```bash
target/site/jacoco/index.html
```
e abra no navegador.