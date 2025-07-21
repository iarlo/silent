package me.iarlo.silent.model.nota;

import me.iarlo.silent.model.cliente.Cliente;
import me.iarlo.silent.model.produto.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Nota {
    private Cliente cliente;
    private List<ItemNota> items = new ArrayList<>();
    private BigDecimal subtotal = BigDecimal.ZERO;

    public Nota(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemNota item) {
        this.items.add(item);
    }

    public void calcularSubtotal() {
        this.subtotal = BigDecimal.ZERO;
        for (ItemNota item : items) {
            Produto produto = item.getProduto();
            BigDecimal precoTotal = produto.getPrecoBase().multiply(BigDecimal.valueOf(item.getQuantidade()));
            this.subtotal = this.subtotal.add(precoTotal);
        }
    }

    public void exibirResumo(int index) {
        System.out.printf("\n--- NOTA FISCAL %d ---%n", index + 1);
        System.out.printf("Cliente: %s (%s)%n", cliente.getNome(), cliente.getCadastroPessoa());
        for (ItemNota item : items) {
            System.out.printf("Produto: %s | Qtd: %d | Preço: R$ %.2f%n",
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getProduto().getPrecoBase());
        }
        System.out.printf("Subtotal: R$ %.2f%n", this.subtotal);
        System.out.println("-----------------------------\n");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemNota> getItems() {
        return items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
