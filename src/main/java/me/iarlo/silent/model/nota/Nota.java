package me.iarlo.silent.model.nota;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Nota {
    private final LocalDateTime data;
    private List<ItemNota> items;
    private BigDecimal subtotal;

    public Nota() {
        this.data = LocalDateTime.now();
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    public void exibirResumo(int index) {
        System.out.printf("%n--- NOTA %d - %s ---%n", index + 1, this.data);
        for (ItemNota item : this.items) {
            BigDecimal preco = item.getProduto().getPrecoBase().multiply(BigDecimal.valueOf(item.getQuantidade()));
            System.out.printf("  %-25s (qnt. %d) R$ %s%n", item.getProduto().getNome(), item.getQuantidade(), preco);
        }
        System.out.printf("Subtotal: %25s", this.getSubtotal());
        System.out.printf("%n----------------------------%n");
    }
}
