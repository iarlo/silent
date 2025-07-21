package me.iarlo.silent.model.nota;

import me.iarlo.silent.model.cliente.Cliente;
import me.iarlo.silent.model.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Nota {
    private final String numeroNota;
    private final LocalDateTime dataEmissao;
    private Cliente cliente;
    private List<ItemNota> items = new ArrayList<>();
    private BigDecimal subtotal = BigDecimal.ZERO;

    private static int contadorNotas = 0;

    public Nota(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        contadorNotas++;
        this.numeroNota = String.format("%06d", contadorNotas);
        this.dataEmissao = LocalDateTime.now();
        this.cliente = cliente;
    }

    public void adicionarItem(ItemNota item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.printf("\n--- NOTA FISCAL %d ---%n", index + 1);
        System.out.printf("Nota nº: %s | Data: %s%n", numeroNota, dataEmissao.format(formatter));
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

    public String getNumeroNota() {
        return numeroNota;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
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
