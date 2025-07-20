package me.iarlo.silent.model.nota;

import me.iarlo.silent.model.produto.Produto;

public class ItemNota {
    private final Produto produto;
    private final int quantidade;

    public ItemNota(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }
}
