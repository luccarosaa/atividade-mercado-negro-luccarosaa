# 🛒 Carrinho Cabuloso - Sistema de Compras com Descontos Aleatórios

## 📋 Sobre o Projeto

Sistema de carrinho de compras em Java que aplica descontos automáticos baseados em sorteio aleatório. O sistema seleciona produtos aleatoriamente para receber descontos, com valores variáveis dependendo do dia da semana.

## 🎯 Funcionalidades

- **🎲 Sorteio Aleatório**: Produtos são selecionados aleatoriamente para receber descontos
- **📅 Descontos por Dia**: Valores de desconto variam conforme o dia da semana
- **🛍️ Gestão de Carrinho**: Adicionar, remover e visualizar produtos
- **💰 Cálculo Automático**: Sistema calcula totais e descontos automaticamente
- **📊 Resumo Detalhado**: Exibe relatório completo da compra

## 🎰 Sistema de Descontos

### Regras de Sorteio:
- **Quantidade**: `número_de_itens / 2` produtos recebem desconto
- **Seleção**: Produtos escolhidos aleatoriamente sem repetição
- **Valor**: Desconto base no dia + fator aleatório por produto

### Valores por Dia:
- **Dias Ímpares** (1,3,5,7): 10% base + 1-10% aleatório
- **Dias Pares** (2,4,6): 5% base + 1-10% aleatório
- **Máximo**: 50% de desconto por produto

### 🏗️ Estrutura do Projeto
carrinho-cabuloso/<br>
├── src/main/java/com/carrinhocabuloso/<br>
│   ├── Main.java           # Interface do usuário e menu<br>
│   ├── Carrinho.java       # Lógica do carrinho e cálculos<br>
│   ├── Cupom.java          # Sistema de descontos e sorteio<br>
│   ├── Produto.java        # Modelo de produto<br>
│   └── ItemCarrinho.java   # Produto + quantidade<br>
│   pom.xml                 # Configuração Maven<br>
└── README.md               # Este arquivo

## 📦 Produtos Disponíveis

| Ossada de boi | R$ 10,00 |<br>
| Pata de Gobrin | R$ 50,00 |<br>
| Presas de Lobo | R$ 11,00 |<br>
| Cabeça de Ave | R$ 40,00 |<br>
| Maça Pooi | R$ 5,00 |<br>
| Maça envenenada | R$ 20,00 |

## 🚀 Como Executar

### 🎮 Como Usar
- Selecione o dia da semana (1-7) no início
- Adicione produtos ao carrinho escolhendo do menu
- Visualize o carrinho a qualquer momento
- Finalize a compra para aplicar descontos automaticamente
- Veja o resumo com todos os detalhes da compra

## 👨‍💻 Desenvolvido por
Lucca Rosa Soares de Souza - lucca.rosa@aluno.ifsp.edu.br

### Comandos:
```bash
# Clone o repositório
git clone <[https://github.com/profrenatomontanher/atividade-mercado-negro-luccarosaa.git]>

# Entre na pasta do projeto
cd carrinho-cabuloso

# Compile o projeto
mvn compile

# Execute o sistema
mvn exec:java -Dexec.mainClass="com.carrinhocabuloso.Main"
