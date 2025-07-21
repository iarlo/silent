package me.iarlo.silent.ui;

import me.iarlo.silent.model.cliente.Cliente;
import me.iarlo.silent.model.cliente.PessoaFisica;
import me.iarlo.silent.model.cliente.PessoaJuridica;
import me.iarlo.silent.model.produto.Produto;
import me.iarlo.silent.model.produto.ProdutoDigital;
import me.iarlo.silent.model.produto.ProdutoFisico;
import me.iarlo.silent.model.produto.ProdutoPerecivel;
import me.iarlo.silent.service.LojaService;
import me.iarlo.silent.util.Cores;
import me.iarlo.silent.util.InputUtils;
import me.iarlo.silent.util.OutputUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleMenu {
    private final Scanner scanner;

    private final LojaService lojaService;
    private final String[] menu;
    private final int linhas;

    private final InputUtils input;
    private final OutputUtils output;

    public ConsoleMenu(LojaService lojaService, String[] menu) {
        this.scanner = new Scanner(System.in);
        this.input = new InputUtils(scanner);
        this.output = new OutputUtils();
        this.lojaService = lojaService;
        this.menu = menu;
        this.linhas = this.output.calcularLinhasNecessarias(menu.length);
    }

    public void menuPrincipal() throws IOException, InterruptedException {
        int opcao;
        do {
            this.output.mudarTela();
            this.imprimirMenu();

            opcao = this.input.lerInteiro("Digite a opção desejada: ");
            this.scanner.nextLine();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    this.telaCadastrarProduto();
                    break;
                case 2:
                    this.telaAlterarProduto();
                    break;
                case 3:
                    this.telaCadastrarCliente();
                    break;
                case 4:
                    this.telaAlterarCliente();
                    break;
                case 5:
                    this.telaCriarNota();
                    break;
                case 6:
                    this.telaListarNotas();
                    break;
                case 7:
                    this.telaListarProdutos();
                    break;
                case 8:
                    this.telaListarClientes();
                    break;
                default:
                    this.input.lerString("\nOpção inválida! Pressione Enter para tentar novamente. ");
            }
        } while (opcao != 0);

        this.output.mudarTela();
        System.out.println("Fechando o programa...");

        this.scanner.close();
    }

    private void telaCadastrarProduto() throws IOException, InterruptedException {
        this.output.mudarTela();
        Produto produto = null;
        Character tipoProduto;
        String codigo;
        String nome;
        BigDecimal precoBase;

        System.out.printf("CADASTRO DE PRODUTO%n");
        codigo = this.input.lerString("Digite um código para o produto (Ou ENTER para gerar automaticamente): ");
        nome = this.input.lerString("Nome do produto: ");
        precoBase = this.input.lerPreco("Preço base: ");
        tipoProduto = this.input.lerCaractere("Qual o tipo do produto? ([D]igital, [F]ísico, [P]erecível) ");

        if (codigo.isEmpty()) codigo = UUID.randomUUID().toString();

        switch (tipoProduto) {
            case 'D' -> produto = new ProdutoDigital(codigo, nome, precoBase);
            case 'F' -> produto = new ProdutoFisico(codigo, nome, precoBase);
            case 'P' -> produto = new ProdutoPerecivel(codigo, nome, precoBase);
            default -> {
                System.out.println("\nOpção inválida! Pressione Enter para tentar novamente.");
                this.scanner.nextLine();
            }
        }

        boolean resultado = this.lojaService.adicionarProduto(produto);

        if (!resultado) {
            OutputUtils.erro("Ocorreu um erro ao cadastrar o produto");
            return;
        }

        System.out.println("Produto cadastrado com sucesso");
    }

    private void telaAlterarProduto() throws IOException, InterruptedException {
        this.output.mudarTela();
        this.telaListarProdutos();
        String codigo = this.input.lerString("Digite o código do produto: ");
        Produto produto = this.lojaService.buscarProdutoPorCodigo(codigo);
        if (produto == null) {
            OutputUtils.erro("Produto não encontrado");
            return;
        }
        BigDecimal preco = this.input.lerPreco("Novo preço: ");
        int estoque = this.input.lerInteiro("Novo estoque: ");
        produto.setPrecoBase(preco);
        produto.setEstoque(estoque);
        System.out.println("Produto atualizado com sucesso.");
    }
    
    private void telaCadastrarCliente() throws IOException, InterruptedException {
        this.output.mudarTela();
        Cliente cliente = null;
        String identificador;
        String nome;
        String endereco;
        String telefone;
        Character tipoCliente;

        System.out.printf("CADASTRO DE CLIENTE%n");
        nome = this.input.lerString("Nome do cliente: ");
        telefone = this.input.lerString("Número de telefone: ");
        endereco = this.input.lerString("Endereço: ");
        tipoCliente = this.input.lerCaractere("Qual o tipo de cliente? ([F]ísico, [J]urídico) ");

        switch (tipoCliente.toString().toLowerCase()) {
            case "f" -> {
                identificador = this.input.lerString("CPF do cliente:");
                cliente = new PessoaFisica(identificador, nome, endereco, telefone);
            }
            case "j" -> {
                identificador = this.input.lerString("CNPJ do cliente:");
                cliente = new PessoaJuridica(identificador, nome, endereco, telefone);
            }
            default -> {
                System.out.println("\nOpção inválida! Pressione Enter para tentar novamente.");
                this.scanner.nextLine();
            }
        }

        boolean resultado = this.lojaService.adicionarCliente(cliente);

        if (!resultado) {
            OutputUtils.erro("Ocorreu um erro ao cadastrar o cliente");
            return;
        }

        System.out.println("Cliente cadastrado com sucesso");
    }

    private void telaAlterarCliente() throws IOException, InterruptedException {
        this.output.mudarTela();
        this.telaListarClientes();
        String codigo = this.input.lerString("Digite o código do cliente: ");
        Cliente cliente = this.lojaService.buscarClientePorCodigo(codigo);
        if (cliente == null) {
            OutputUtils.erro("Cliente não encontrado");
            return;
        }
        cliente.setNome(this.input.lerString("Novo nome: "));
        cliente.setEndereco(this.input.lerString("Novo endereço: "));
        cliente.setTelefone(this.input.lerString("Novo telefone: "));
        System.out.println("Cliente atualizado com sucesso.");
    }

    private void telaCriarNota() throws IOException, InterruptedException {
        this.output.mudarTela();
        this.telaListarClientes();
        String codigo = this.input.lerString("Digite o código do cliente: ");
        Cliente cliente = this.lojaService.buscarClientePorCodigo(codigo);
        if (cliente == null) {
            OutputUtils.erro("Cliente não encontrado");
            return;
        }
        Nota nota = new Nota(cliente);
        while (true) {
            this.telaListarProdutos();
            String codigoProduto = this.input.lerString("Digite o código do produto (ou 'fim' para encerrar): ");
            if (codigoProduto.equalsIgnoreCase("fim")) break;
            Produto produto = this.lojaService.buscarProdutoPorCodigo(codigoProduto);
            if (produto == null) {
                OutputUtils.erro("Produto não encontrado");
                continue;
            }
            int qtd = this.input.lerInteiro("Quantidade: ");
            nota.adicionarItem(new ItemNota(produto, qtd));
        }
        nota.calcularSubtotal();
        this.lojaService.adicionarNotaFiscal(nota);
        System.out.println("Nota criada com sucesso!");
        nota.exibirResumo(0);
    }
    
    private void telaListarProdutos() throws IOException, InterruptedException {
        this.output.mudarTela();
        for (int i = 0; i < this.lojaService.getProdutos().size(); i++)
            this.lojaService.getProdutos().get(i).exibirResumo(i);
        this.input.lerString("Aperte ENTER para voltar ");
    }

    private void telaListarClientes() throws IOException, InterruptedException {
        this.output.mudarTela();
        for (int i = 0; i < this.lojaService.getClientes().size(); i++)
            this.lojaService.getClientes().get(i).exibirResumo(i);
        this.input.lerString("Aperte ENTER para voltar ");
    }

    private void telaListarNotas() throws IOException, InterruptedException {
        this.output.mudarTela();
        for (int i = 0; i < this.lojaService.getNotasFiscais().size(); i++)
            this.lojaService.getNotasFiscais().get(i).exibirResumo(i);
        this.input.lerString("Aperte ENTER para voltar ");
    }

    private void imprimirMenu() {
        for (int i = 0; i < this.linhas; i++)
            System.out.print(this.criarLinha(i));
        System.out.printf("%s%n0. Sair%s%n%n", Cores.RED.get(), Cores.RESET.get());
    }

    private String criarLinha(final int indice) {
        OutputUtils.Indice i = (int j) -> "\u001B[34m" + (j + 1) + ".\u001B[0m ";
        String primeiraColuna = i.formatar(indice) + this.menu[indice];
        String segundaColuna = (indice + this.linhas) < this.menu.length ? i.formatar(indice + this.linhas) + this.menu[indice + linhas] : "";
        return String.format("%-35s%s%n", primeiraColuna, segundaColuna);
    }
}
