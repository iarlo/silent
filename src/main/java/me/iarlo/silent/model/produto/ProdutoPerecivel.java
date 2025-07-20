package me.iarlo.silent.model.produto;

import java.math.BigDecimal;

public class ProdutoPerecivel extends Produto {
    public ProdutoPerecivel(String codigo, String nome, BigDecimal precoBase) {
        super(codigo, nome, precoBase);
    }

    @Override
    public String getTipo() {
        return "Perecível";
    }
}