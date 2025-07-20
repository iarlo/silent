package me.iarlo.silent.model.produto;

import java.math.BigDecimal;

public class ProdutoDigital extends Produto {
    public ProdutoDigital(String codigo, String nome, BigDecimal precoBase) {
        super(codigo, nome, precoBase);
    }

    @Override
    public String getTipo() {
        return "Digital";
    }
}