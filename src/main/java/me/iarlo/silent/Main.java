package me.iarlo.silent;

import me.iarlo.silent.service.LojaService;
import me.iarlo.silent.ui.ConsoleMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        LojaService loja = new LojaService();

        ConsoleMenu programa = new ConsoleMenu(loja, new String[]{
                "Cadastrar Produto", "Alterar Produto",
                "Cadastrar Cliente", "Alterar Cliente",
                "Criar Nota de Compra", "Listar Notas Emitidas",
                "Listar Produtos", "Listar Clientes",
        });

        programa.menuPrincipal();
    }
}