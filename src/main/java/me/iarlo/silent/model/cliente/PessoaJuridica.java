package me.iarlo.silent.model.cliente;

public class PessoaJuridica extends Cliente {
    public PessoaJuridica(String codigoUnico, String nome, String endereco, String telefone) {
        super(codigoUnico, nome, endereco, telefone);
    }

    @Override
    public String getTipo() {
        return "Jurídica";
    }

    @Override
    public String getCadastroPessoa() {
        return this.cadastroPessoa.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3.$4-$5");
    }

    @Override
    public boolean setCadastroPessoa(String cadastro) {
        if (cadastro.length() != 14 || !cadastro.matches("\\d+")) return false;
        this.cadastroPessoa = cadastro;
        return true;
    }
}