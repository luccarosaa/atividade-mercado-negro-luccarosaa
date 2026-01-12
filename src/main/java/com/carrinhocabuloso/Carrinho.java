package com.carrinhocabuloso;

import java.util.*;

public class Carrinho {
    private List<ItemCarrinho> itens;
    private List<Cupom> cuponsAtivos;
    
    public Carrinho() {
        this.itens = new ArrayList<>();
        this.cuponsAtivos = new ArrayList<>();
    }
    
    public void adicionarProduto(Produto produto, int quantidade) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().getNome().equals(produto.getNome())) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                System.out.println("Produto atualizado no carrinho!");
                return;
            }
        }
        
        itens.add(new ItemCarrinho(produto, quantidade));
        System.out.println("Produto adicionado ao carrinho!");
    }
    
    public void removerProduto(String nomeProduto) {
        itens.removeIf(item -> item.getProduto().getNome().equalsIgnoreCase(nomeProduto));
        System.out.println("Produto removido do carrinho!");
    }
    
    // Gera cupons automáticos para produtos aleatórios
    public void gerarCuponsAutomaticos(int diaDaSemana) {
        int produtosComDesconto = itens.size() / 2;
        
        if (produtosComDesconto < 1) {
            cuponsAtivos.clear();
            return;
        }
        
        // Gera cupons automáticos para produtos aleatórios
        cuponsAtivos = Cupom.gerarCuponsAutomaticos(itens, diaDaSemana);
    }
    
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrinho item : itens) {
            // Procura se há cupom para este produto específico
            Cupom cupom = encontrarCupomParaProduto(item.getProduto());
            total += item.calcularSubtotalComDesconto(cupom);
        }
        return total;
    }
    
    // Encontra cupom para um produto específico
    private Cupom encontrarCupomParaProduto(Produto produto) {
        for (Cupom cupom : cuponsAtivos) {
            if (cupom.aplicavelA(produto)) {
                return cupom;
            }
        }
        return null;
    }
    
    public double calcularTotalSemDesconto() {
        double total = 0;
        for (ItemCarrinho item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
    
    // Calcula o percentual total de desconto aplicado
    public double calcularPercentualDesconto() {
        double totalSemDesconto = calcularTotalSemDesconto();
        double totalComDesconto = calcularTotal();
        
        if (totalSemDesconto == 0) return 0;
        
        return ((totalSemDesconto - totalComDesconto) / totalSemDesconto) * 100;
    }
    
    public void exibirCarrinho() {
        System.out.println("\n=== CARRINHO ===");
        
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }
        
        System.out.println("ITENS NO CARRINHO:");
        for (int i = 0; i < itens.size(); i++) {
            ItemCarrinho item = itens.get(i);
            Cupom cupom = encontrarCupomParaProduto(item.getProduto());
            
            if (cupom != null) {
                System.out.printf("%d. %s | DESCONTO: %.1f%%\n", 
                    i + 1, item, cupom.getPercentualDesconto());
            } else {
                System.out.printf("%d. %s\n", i + 1, item);
            }
        }
        
        System.out.println("\nRESUMO DO PEDIDO:");
        System.out.printf("Subtotal: R$ %.2f\n", calcularTotalSemDesconto());
        System.out.printf("Total: R$ %.2f\n", calcularTotal());
    }
    
    // Exibe o resumo do pedido no formato solicitado
    public void exibirResumoFinal(int diaDaSemana) {
        System.out.println("\n===== RESUMO DO PEDIDO =====");
        System.out.println("Dia da compra: " + getNomeDia(diaDaSemana));
        System.out.println("Quantidade de itens: " + itens.size());
        System.out.println("Produtos com desconto: " + cuponsAtivos.size());
        
        // Lista todos os produtos
        for (ItemCarrinho item : itens) {
            Cupom cupom = encontrarCupomParaProduto(item.getProduto());
            double subtotal = item.calcularSubtotal();
            double subtotalComDesconto = item.calcularSubtotalComDesconto(cupom);
            
            if (cupom != null) {
                System.out.printf("%s (%d) ----- R$ %.2f -> R$ %.2f (%.1f%% off)\n", 
                    item.getProduto().getNome(), 
                    item.getQuantidade(), 
                    subtotal,
                    subtotalComDesconto,
                    cupom.getPercentualDesconto());
            } else {
                System.out.printf("%s (%d) ----- R$ %.2f\n", 
                    item.getProduto().getNome(), 
                    item.getQuantidade(), 
                    subtotal);
            }
        }
        
        double subtotal = calcularTotalSemDesconto();
        double total = calcularTotal();
        double percentualDesconto = calcularPercentualDesconto();
        
        System.out.printf("\nSubtotal ----- R$ %.2f\n", subtotal);
        
        if (percentualDesconto > 0) {
            System.out.printf("Desconto aplicado ----- %.1f%%\n", percentualDesconto);
        } else {
            System.out.println("Desconto aplicado ----- Sem desconto aplicado");
        }
        
        System.out.printf("Total ----- R$ %.2f\n", total);
        
        // Mostra a regra aplicada
        System.out.println("\n--- Regra aplicada ---");
        System.out.println("Quantidade de itens: " + itens.size());
        System.out.println("Itens com desconto: " + itens.size() + " / 2 = " + (itens.size() / 2));
        System.out.println("Dia " + diaDaSemana + " (" + getNomeDia(diaDaSemana) + ")");
        System.out.println("Tipo: " + (diaDaSemana % 2 == 0 ? "PAR - descontos menores" : "IMPAR - descontos maiores"));
    }
    
    // Função que recebe o dia da semana como parâmetro
    public void finalizarCompra(int diaDaSemana) {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio! Adicione produtos antes de finalizar.");
            return;
        }
        
        // Gera cupons automáticos antes de finalizar usando o dia selecionado
        gerarCuponsAutomaticos(diaDaSemana);
        
        // Exibe o resumo final com preço total sem e com desconto
        exibirResumoFinal(diaDaSemana);
        
        System.out.println("\nObrigado pela compra no Cabuloso Market!");
        System.out.println("Volte sempre!\n");
        
        itens.clear();
        cuponsAtivos.clear();
    }
    
    // Getters para testes
    public List<ItemCarrinho> getItens() { return itens; }
    public List<Cupom> getCuponsAtivos() { return cuponsAtivos; }
}
