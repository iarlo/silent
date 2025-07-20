package me.iarlo.silent.model.cliente;

public class PessoaFisica extends Cliente {
    public PessoaFisica(String codigoUnico, String nome, String endereco, String telefone) {
        super(codigoUnico, nome, endereco, telefone);
    }

    @Override
    public String getTipo() {
        return "Física";
    }

    @Override
    public String getCadastroPessoa() {
        return this.cadastroPessoa.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    @Override
    public boolean setCadastroPessoa(String cadastro) {
        if (cadastro.length() != 11 || !cadastro.matches("\\d+")) return false;
        this.cadastroPessoa = cadastro;
        return true;
    }
}
