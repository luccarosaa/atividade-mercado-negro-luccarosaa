package com.carrinhocabuloso;

import java.util.*;

public class Cupom {
    private String codigo;
    private double percentualDesconto;
    private String produtoAplicavel; // Nome do produto específico
    
    public Cupom(String codigo, double percentualDesconto, String produtoAplicavel) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.produtoAplicavel = produtoAplicavel;
    }
    
    // Gera cupons aleatórios para produtos específicos
    public static List<Cupom> gerarCuponsAutomaticos(List<ItemCarrinho> itens, int diaDaSemana) {
        List<Cupom> cupons = new ArrayList<>();
        Random random = new Random();
        
        // Calcula quantos produtos terão desconto (quantidade / 2)
        int produtosComDesconto = itens.size() / 2;
        
        if (produtosComDesconto < 1) {
            return cupons; // Sem descontos
        }
        
        // Cria uma cópia da lista para não modificar a original
        List<ItemCarrinho> itensDisponiveis = new ArrayList<>(itens);
        
        // Gera cupons para produtos aleatórios
        for (int i = 0; i < produtosComDesconto && !itensDisponiveis.isEmpty(); i++) {
            // Escolhe um produto aleatório da lista
            int indexAleatorio = random.nextInt(itensDisponiveis.size());
            ItemCarrinho itemSelecionado = itensDisponiveis.get(indexAleatorio);
            String nomeProduto = itemSelecionado.getProduto().getNome();
            
            // Calcula desconto baseado no dia + fator aleatório do produto
            double descontoBase = getDescontoBaseDoDia(diaDaSemana);
            double fatorProduto = (random.nextDouble() * 10) + 1; // 1% a 10% extra
            double descontoFinal = descontoBase + fatorProduto;
            
            // Limita a no máximo 50% de desconto
            descontoFinal = Math.min(descontoFinal, 50);
            
            String codigo = "DESC" + (i + 1);
            cupons.add(new Cupom(codigo, descontoFinal, nomeProduto));
            
            // Remove o produto selecionado para não repetir
            itensDisponiveis.remove(indexAleatorio);
        }
        
        return cupons;
    }
    
    // Retorna desconto base baseado no dia
    private static double getDescontoBaseDoDia(int diaDaSemana) {
        if (diaDaSemana % 2 == 0) { // Dias pares (2, 4, 6)
            return 5.0; // Desconto base menor
        } else { // Dias ímpares (1, 3, 5, 7)
            return 10.0; // Desconto base maior
        }
    }
    
    // Verifica se cupom se aplica a um produto específico
    public boolean aplicavelA(Produto produto) {
        return produtoAplicavel.equals(produto.getNome());
    }
    
    // Getters
    public String getCodigo() { return codigo; }
    public double getPercentualDesconto() { return percentualDesconto; }
    public String getProdutoAplicavel() { return produtoAplicavel; }
}
