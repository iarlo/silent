package me.iarlo.silent.model.cliente;

public abstract class Cliente {
    private final String codigoUnico;
    protected String cadastroPessoa;
    private String nome;
    private String endereco;
    private String telefone;

    public Cliente(String codigoUnico, String nome, String endereco, String telefone) {
        this.codigoUnico = codigoUnico;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCodigoUnico() {
        return this.codigoUnico;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void exibirResumo(int index) {
        System.out.printf("%n--- CLIENTE %d - PESSOA %s ---%n", index + 1, this.getTipo().toUpperCase());
        System.out.printf("Nome: %s  |  CPF/CNPJ: %s%n", this.getNome(), this.getCadastroPessoa());
        System.out.printf("Telefone: %s  |  Endereço: %s", this.getTelefone(), this.getEndereco());
        System.out.printf("%n----------------------------%n");
    }

    public abstract String getTipo();

    public abstract String getCadastroPessoa();

    public abstract boolean setCadastroPessoa(String numero);
}
