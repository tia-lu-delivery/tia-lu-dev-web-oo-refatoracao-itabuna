# 🍔 Sistema de Pedidos - Refatoração com Padrões de Projeto

# Disclaimer Geral
Professor, essa pull request é uma re-implementação do repositório https://github.com/NatanCorreiaS/tia-lu-dev-web-oo-refatoracao-itabuna, devido a um mal entendimento meu, refatorei o código da OAT1 com o padrão strategy inteiro.
## 📌 Visão Geral
Este repositório demonstra uma refatoração de um sistema de pedidos utilizando diversos padrões de projeto com foco em modularidade, testabilidade e manutenção. Entre os padrões aplicados estão Builder, State, Composite, Observer, Strategy, Facade e Singleton.

---

## 🎯 Padrão Strategy — Pull Request Itabuna

### O que é
O padrão Strategy (Estratégia) define uma família de algoritmos intercambiáveis, encapsula cada um e os torna intercambiáveis no tempo de execução. Ele permite que o algoritmo varie independentemente dos clientes que o utilizam.

### Como foi aplicado no projeto
Neste sistema o padrão Strategy foi aplicado para a geração de relatórios. Existem (pelo menos) duas implementações de relatório:
- `RelatorioDetalhadoStrategy` — gera uma listagem detalhada dos pedidos (ID, cliente, itens, subtotais e total).
- `RelatorioSimplificadoStrategy` — gera um resumo do dia (número de pedidos e valor total arrecadado).

Ambas as estratégias expõem um método `gerarRelatorio()` que é chamado pela camada que precisa apresentar o relatório (por exemplo, pela CLI ou por uma fachada). As estratégias acessam os dados através de um repositório central (Singleton) que reúne clientes, cardápio e pedidos.

### Por que usar Strategy aqui
- Separação de responsabilidades: a lógica de apresentação/formatacao dos relatórios fica isolada da lógica de negócio.
- Extensibilidade: novas estratégias de relatório (por exemplo, relatórios por período, por cliente ou em CSV) podem ser adicionadas sem alterar o cliente que as usa.
- Testabilidade: cada estratégia pode ser testada isoladamente fornecendo um repositório com dados de teste.

---

## ✅ Vantagens do uso de Strategy no projeto
- Flexibilidade para trocar a forma de geração de relatórios em tempo de execução.
- Código mais limpo e de fácil manutenção: evita longos if/else ou switch para escolher o formato do relatório.
- Facilita a adição de novos formatos de relatório sem modificar código já existente (princípio aberto/fechado).
- Permite criar estratégias especializadas para diferentes canais (console, arquivo, API) sem misturar responsabilidades.

## ⚠️ Desvantagens / pontos de atenção
- Aumento no número de classes: para cada variação do algoritmo cria-se uma nova classe, o que pode trazer sobrecarga cognitiva se houver muitas variantes.
- Necessidade de um mecanismo para selecionar a estratégia adequada em tempo de execução (fábrica ou injeção de dependência), caso a seleção seja complexa.
- Se as estratégias dependerem de acesso a dados centralizados, é preciso garantir que o ponto único de acesso (singleton/repositório) esteja bem testado e documentado para evitar acoplamento indesejado.

---

## Testes unitários
Foram considerados testes unitários para validar cada estratégia e os componentes centrais, mas no estado atual do repositório NÃO há testes automatizados.

Testes unitários realizados?
- [ ] Sim
- [x] Não

> Observação: é recomendado adicionar pelo menos testes unitários para as estratégias (`RelatorioDetalhadoStrategy`, `RelatorioSimplificadoStrategy`) e para o `Repository`/camada de dados para garantir comportamento estável durante refatorações.

---

## Como testar os relatórios com o padrão Strategy
A aplicação inclui um método de demonstração chamado `testStrategy()` (implementado na CLI) que popula dados de exemplo no `Repository` e executa os dois relatórios: o simplificado e o detalhado. Siga os passos abaixo para usar esse teste manualmente:

Passos rápidos:
1. Build do projeto e execução com o Gradle.

- Alternativamente, execute a classe principal com o classpath do build (após `./gradlew build`) ou abra o projeto na sua IDE e execute `TiaLuDeliveryApplication`.

2. No menu da CLI digite `1` e pressione Enter. Isso chama `testStrategy()` que:
- limpa dados anteriores do `Repository` para tornar o teste determinístico;
- cria dois itens de cardápio (por exemplo, "Hambúrguer" e "Refrigerante");
- cria dois clientes e dois pedidos; e
- executa `RelatorioSimplificadoStrategy` e `RelatorioDetalhadoStrategy` em sequência.

Saída esperada (exemplo):

--- Relatório Simplificado ---
Resumo do Dia:
Total de pedidos: 2
Valor total arrecadado: R$ 37.50

--- Relatório Detalhado ---
Pedidos do Dia:
Pedido ID: 1
Cliente: Ana
Status: AWAITING_MERCHANT_ACCEPTANCE
Itens:
- Hambúrguer x2 = 25.00
- Refrigerante x1 = 4.00
Total: 29.00
----------------------
Pedido ID: 2
Cliente: Bruno
Status: AWAITING_MERCHANT_ACCEPTANCE
Itens:
- Refrigerante x3 = 12.00
Total: 12.00
----------------------

Observações:
- Os valores e o formato exato podem variar conforme o conteúdo do `MenuItem` e do `Order.total()`.
- O método `testStrategy()` foi implementado apenas para demonstração e não deve ser usado em produção; para testes automatizados, crie casos unitários que injetem ou configurem o `Repository` apropriado.

---

## Conclusão
A adoção do padrão Strategy para gerar relatórios neste projeto trouxe clareza e modularidade ao código, facilitando a evolução das diferentes formas de apresentação de dados. Para cenários onde existem múltiplas formas de executar uma mesma operação (como relatórios, ordenações, cálculos ou validações), Strategy é uma escolha adequada. Porém é importante equilibrar o número de estratégias com a simplicidade do sistema e manter um mecanismo claro de seleção/injeção das estratégias.

---

## 👥 Equipe
A equipe responsável por este projeto é composta por:
- Natan Correia
- Rayan Santos
- Igor dos Santos Vieira
- Clara Gabryellen
- Jhon Luiz
