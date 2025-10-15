🏦 Projeto Banco - Sistema de Contas e Transações
Este projeto Java simula a lógica de negócio básica de um sistema bancário, focado na classe Conta e em um TransacaoService. O projeto utiliza Apache Maven como gerenciador de build e JUnit 5 para testes unitários, com cobertura verificada pelo JaCoCo.

🚀 Requisitos
Para executar e testar o projeto, você precisará ter instalado:

JDK (Java Development Kit): Versão 17 ou superior.

Apache Maven: Versão 3.x ou superior.

IDE (Opcional): Apache NetBeans, IntelliJ IDEA ou Eclipse.

⚙️ Configuração e Execução
1. Clonar o Repositório
Navegue até o diretório onde você deseja salvar o projeto e clone o repositório (se estiver usando Git):

Bash

git clone <URL_DO_REPOSITORIO>
cd banco
2. Compilar o Projeto
Execute o comando Maven para baixar as dependências e compilar o código fonte e os testes:

Bash

mvn clean install
✅ Execução dos Testes e Cobertura (JaCoCo)
O projeto está configurado para executar todos os testes unitários e, em seguida, gerar o relatório de cobertura de código (Code Coverage) usando o plugin JaCoCo.

O build está configurado para falhar se a cobertura de linhas for inferior a 75% (jacoco-check).

Para executar o ciclo completo (testes + geração de relatório + verificação), utilize o goal verify:

Bash

mvn clean verify
Resultados Esperados
Sucesso do Build: Se a cobertura de código for igual ou superior a 75%, o Maven exibirá BUILD SUCCESS.

Falha do Build: Se a cobertura for inferior a 75% (conforme sua última tentativa), o Maven exibirá BUILD FAILURE e indicará a métrica que não atingiu o mínimo no goal jacoco-check.

📈 Visualizar o Relatório JaCoCo
Após a execução do comando mvn clean verify, o relatório de cobertura em formato HTML é gerado.

O relatório estará disponível no seguinte caminho, dentro da pasta do projeto:

target/site/jacoco/index.html
Para visualizar o relatório:

Navegue até a pasta target/site/jacoco/.

Abra o arquivo index.html no seu navegador de preferência.

O relatório usará cores para indicar o status do código:

Verde: Linhas cobertas por testes.

Amarelo: Branches (decisões) parcialmente cobertos.

Vermelho: Linhas não cobertas (que precisam de testes).

Execução na IDE (NetBeans)
Se estiver usando o Apache NetBeans (ou similar):

Abrir o Projeto: Use File → Open Project e selecione o pom.xml.

Executar Testes e Cobertura:

Clique com o botão direito no projeto, no painel Projects.

Selecione Run Maven → Goals.

Clique em Run.

Localizar o Relatório: O relatório HTML pode ser acessado na aba Files → target/site/jacoco/index.html.