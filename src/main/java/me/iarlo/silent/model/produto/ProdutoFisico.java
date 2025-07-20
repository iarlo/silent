package me.iarlo.silent.model.produto;

import java.math.BigDecimal;

public class ProdutoFisico extends Produto {
    public ProdutoFisico(String codigo, String nome, BigDecimal precoBase) {
        super(codigo, nome, precoBase);
    }

    @Override
    public String getTipo() {
        return "Físico";
    }
}
