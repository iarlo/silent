package me.iarlo.silent.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtils {
    private final Scanner scanner;

    public InputUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public String lerString(String descricao) {
        System.out.print(descricao);
        return this.scanner.nextLine().trim();
    }

    public int lerInteiro(String descricao) {
        System.out.print(descricao);
        return this.scanner.nextInt();
    }

    public Character lerCaractere(String descricao) {
        System.out.print(descricao);
        String linha = this.scanner.nextLine().trim().toUpperCase();
        return linha.isEmpty() ? null : linha.charAt(0);
    }

    public BigDecimal lerPreco(String descricao) {
        System.out.print(descricao);
        try {
            String preco = this.scanner.nextLine().replace(',', '.');
            return new BigDecimal(preco);
        } catch (Exception e) {
            OutputUtils.erro("O valor informado é inválido. Tente usar '.' como separador.");
            System.exit(0);
        }
        return null;
    }
}
