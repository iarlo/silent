package me.iarlo.silent.model.produto;

import java.math.BigDecimal;

public abstract class Produto {
    private final String codigoUnico;
    private final String nome;
    private int estoque;
    private BigDecimal precoBase;

    protected Produto(String codigo, String nome, BigDecimal precoBase) {
        this.codigoUnico = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.estoque = 0;
    }

    public String getCodigoUnico() {
        return this.codigoUnico;
    }

    public String getNome() {
        return this.nome;
    }

    public BigDecimal getPrecoBase() {
        return this.precoBase;
    }

    public void setPrecoBase(BigDecimal preco) {
        this.precoBase = preco;
    }

    public int getEstoque() {
        return this.estoque;
    }

    public void setEstoque(int quantidade) {
        this.estoque = quantidade;
    }

    public void exibirResumo(int index) {
        System.out.printf("%n--- ITEM %d - PRODUTO %s ---%n", index + 1, this.getTipo().toUpperCase());
        System.out.printf("Nome: %s  |  Código: %s%n", this.getNome(), this.getCodigoUnico());
        System.out.printf("Preço: %s  |  Estoque: %d", this.getPrecoBase(), this.getEstoque());
        System.out.printf("%n---------------------------------%n");
    }

    public abstract String getTipo();
}
