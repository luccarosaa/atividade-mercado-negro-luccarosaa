package com.carrinhocabuloso;

import java.util.Scanner;

public class Main {
    private static int diaSelecionado = 1; // Default: Domingo
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        
        // Primeiro: Selecionar o dia da semana
        selecionarDiaDaSemana(scanner);
        
        // Produtos com preços corretos
        Produto[] produtos = {
            new Produto("Ossada de boi", 10.00),
            new Produto("Pata de Gobrin", 50.00),
            new Produto("Presas de Lobo", 11.00),
            new Produto("Cabeca de Ave", 40.00),
            new Produto("Maca Pooi", 5.00),
            new Produto("Maca envenenada", 20.00)
        };
        
        System.out.println("==================================");
        System.out.println("       CARRINHO CABULOSO");
        System.out.println("==================================");
        System.out.println("Dia selecionado: " + getNomeDia(diaSelecionado));
        System.out.println("Tipo de dia: " + (diaSelecionado % 2 == 0 ? "PAR (descontos menores)" : "IMPAR (descontos maiores)"));
        System.out.println("Sistema de descontos automaticos");
        System.out.println("Baseado na variedade de produtos!");
        
        boolean executando = true;
        
        while (executando) {
            System.out.println("\nMENU PRINCIPAL:");
            System.out.println("1. Ver Produtos Disponiveis");
            System.out.println("2. Adicionar Produto ao Carrinho");
            System.out.println("3. Ver Carrinho");
            System.out.println("4. Remover Produto do Carrinho");
            System.out.println("5. Alterar Dia da Semana");
            System.out.println("6. Finalizar Compra");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opcao: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    System.out.println("\nPRODUTOS DISPONIVEIS:");
                    for (int i = 0; i < produtos.length; i++) {
                        System.out.printf("%d. %s\n", i + 1, produtos[i]);
                    }
                    aguardarEnter(scanner);
                    break;
                    
                case 2:
                    System.out.println("\nADICIONAR PRODUTO:");
                    for (int i = 0; i < produtos.length; i++) {
                        System.out.printf("%d. %s\n", i + 1, produtos[i]);
                    }
                    System.out.print("Escolha o numero do produto: ");
                    int numProduto = scanner.nextInt() - 1;
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    
                    if (numProduto >= 0 && numProduto < produtos.length) {
                        carrinho.adicionarProduto(produtos[numProduto], quantidade);
                    } else {
                        System.out.println("Produto invalido!");
                    }
                    aguardarEnter(scanner);
                    break;
                    
                case 3:
                    carrinho.exibirCarrinho();
                    aguardarEnter(scanner);
                    break;
                    
                case 4:
                    carrinho.exibirCarrinho();
                    if (!carrinho.getItens().isEmpty()) {
                        System.out.print("Digite o nome do produto a remover: ");
                        String nomeRemover = scanner.nextLine();
                        carrinho.removerProduto(nomeRemover);
                    }
                    aguardarEnter(scanner);
                    break;
                    
                case 5:
                    selecionarDiaDaSemana(scanner);
                    System.out.println("Dia alterado para: " + getNomeDia(diaSelecionado));
                    System.out.println("Tipo de dia: " + (diaSelecionado % 2 == 0 ? "PAR (descontos menores)" : "IMPAR (descontos maiores)"));
                    aguardarEnter(scanner);
                    break;
                    
                case 6:
                    // Passa o dia selecionado para o carrinho
                    carrinho.finalizarCompra(diaSelecionado);
                    executando = false;
                    break;
                    
                case 7:
                    System.out.println("Ficamos triste por ir embora :( Obrigado por usar o Cabuloso Market!");
                    executando = false;
                    break;
                    
                default:
                    System.out.println("Opição inválida! Digite um dos números do menu");
                    aguardarEnter(scanner);
            }
        }
    }
    
    // Método para selecionar o dia da semana
    private static void selecionarDiaDaSemana(Scanner scanner) {
        System.out.println("\n==================================");
        System.out.println("    SELECIONE O DIA DA SEMANA");
        System.out.println("==================================");
        System.out.println("1 - Domingo");
        System.out.println("2 - Segunda");
        System.out.println("3 - Terca");
        System.out.println("4 - Quarta");
        System.out.println("5 - Quinta");
        System.out.println("6 - Sexta");
        System.out.println("7 - Sabado");
        System.out.print("Escolha o dia (1-7): ");
        
        int dia = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        if (dia >= 1 && dia <= 7) {
            diaSelecionado = dia;
            System.out.println("Dia da compra: " + Main.getNomeDia(diaDaSemana));
        } else {
            System.out.println("Dia inválido! O Domingo será usado como padrão.");
            diaSelecionado = 1;
        }
    }
    
    // Método para obter o nome do dia
    private static String getNomeDia(int dia) {
        switch (dia) {
            case 1: return "Domingo";
            case 2: return "Segunda-feira";
            case 3: return "Terca-feira";
            case 4: return "Quarta-feira";
            case 5: return "Quinta-feira";
            case 6: return "Sexta-feira";
            case 7: return "Sabado";
            default: return "Domingo";
        }
    }
    
    // Getter para o dia selecionado (usado pelo Carrinho)
    public static int getDiaSelecionado() {
        return diaSelecionado;
    }

    // Método para pausar e esperar Enter
    private static void aguardarEnter(Scanner scanner) {
        System.out.print("\nAperte Enter para continuar...");
        scanner.nextLine(); // Espera o usuário pressionar Enter
    }
}
