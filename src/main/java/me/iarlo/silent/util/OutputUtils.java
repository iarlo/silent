package me.iarlo.silent.util;

import java.io.IOException;

public class OutputUtils {
    protected final String[] titulo = {
            "  ____ ___ _     _____ _   _ _____ ",
            " / ___|_ _| |   | ____| \\ | |_   _|",
            " \\___ \\| || |   |  _| |  \\| | | |  ",
            "  ___) | || |___| |___| |\\  | | |  ",
            " |____/___|_____|_____|_| \\_| |_|  ",
            Cores.CYAN_BOLD.get() + "SI" + Cores.RESET.get() + "STEMA DE " +
                    Cores.CYAN_BOLD.get() + "L" + Cores.RESET.get() + "OJA E " +
                    Cores.CYAN_BOLD.get() + "E" + Cores.RESET.get() + "MISSÃO DE " +
                    Cores.CYAN_BOLD.get() + "N" + Cores.RESET.get() + "O" +
                    Cores.CYAN_BOLD.get() + "T" + Cores.RESET.get() + "AS\n"
    };

    public static void erro(Exception e) {
        System.out.printf("%n%s %s%n", Cores.RED.get(), e.getMessage());
    }

    public static void erro(String mensagem) {
        System.out.printf("%n%s %s%n", Cores.RED.get(), mensagem);
    }

    public static void erro(Exception e, String mensagem) {
        System.out.printf("%n%s %s %s%n", Cores.RED.get(), mensagem, e.getMessage());
    }

    protected void imprimirTitulo() {
        for (String s : this.titulo) {
            System.out.println(s);
        }
    }

    public int calcularLinhasNecessarias(int quantidade) {
        return (int) Math.ceil(quantidade / 2.0);
    }

    protected void limparTela() throws InterruptedException, IOException {
        final String sistema = System.getProperty("os.name");
        if (sistema.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            new ProcessBuilder("clear").inheritIO().start().waitFor();
    }

    public void mudarTela() throws IOException, InterruptedException {
        this.limparTela();
        this.imprimirTitulo();
    }

    @FunctionalInterface
    public interface Indice {
        String formatar(int indice);
    }
}
