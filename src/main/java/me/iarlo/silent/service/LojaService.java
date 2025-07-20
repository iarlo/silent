package me.iarlo.silent.service;

import me.iarlo.silent.model.cliente.Cliente;
import me.iarlo.silent.model.nota.Nota;
import me.iarlo.silent.model.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class LojaService {
    private final List<Produto> produtos = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Nota> notasFiscais = new ArrayList<>();

    public boolean adicionarProduto(Produto produto) {
        if (produto == null) return false;
        this.produtos.add(produto);
        return true;
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null) return false;
        this.clientes.add(cliente);
        return true;
    }

    public void adicionarNotaFiscal(Nota nota) {
        this.notasFiscais.add(nota);
    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public List<Nota> getNotasFiscais() {
        return this.notasFiscais;
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }
}
