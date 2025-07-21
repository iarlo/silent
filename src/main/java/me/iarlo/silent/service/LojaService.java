package me.iarlo.silent.service;

import me.iarlo.silent.model.cliente.Cliente;
import me.iarlo.silent.model.nota.Nota;
import me.iarlo.silent.model.produto.Produto;

import java.util.ArrayList;
import java.util.List;

public class LojaService {
    private final List<Produto> produtos;
    private final List<Cliente> clientes;
    private final List<Nota> notas;

    public LojaService() {
        this.produtos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.notas = new ArrayList<>();
    }

    public boolean adicionarProduto(Produto produto) {
        if (produto == null) return false;
        return produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        for (Produto p : produtos) {
            if (p.getCodigoUnico().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null) return false;
        return clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente buscarClientePorCodigo(String codigo) {
        for (Cliente c : clientes) {
            if (c.getCodigoUnico().equalsIgnoreCase(codigo)) {
                return c;
            }
        }
        return null;
    }

    public void adicionarNotaFiscal(Nota nota) {
        if (nota != null) {
            notas.add(nota);
        }
    }

    public List<Nota> getNotasFiscais() {
        return notas;
    }
}
