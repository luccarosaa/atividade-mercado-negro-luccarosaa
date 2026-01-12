package com.carrinhocabuloso;

public class ItemCarrinho {
    private Produto produto;
    private int quantidade;
    
    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }
    
    public double calcularSubtotalComDesconto(Cupom cupom) {
        double subtotal = calcularSubtotal();
        if (cupom != null && cupom.aplicavelA(produto)) {
            return subtotal * (1 - cupom.getPercentualDesconto() / 100);
        }
        return subtotal;
    }
    
    // Getters
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return String.format("%d x %s = R$ %.2f", 
            quantidade, produto.getNome(), calcularSubtotal());
    }
}