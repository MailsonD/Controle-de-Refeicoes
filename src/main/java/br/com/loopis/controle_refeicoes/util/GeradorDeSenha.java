package br.com.loopis.controle_refeicoes.util;

import java.util.Random;

public class GeradorDeSenha {

    private static final char[] ALL_CHARS = new char[62];
    private static final Random RANDOM = new Random();
    private static final int PASSWORD_LEN = 8;

    static {
        for (int i = 48, j = 0; i < 123; i++) {
            if (Character.isLetterOrDigit(i)) {
                ALL_CHARS[j] = (char) i;
                j++;
            }
        }
    }

    public static String gerarSenhaAleatoria() {
        final char[] resultado = new char[PASSWORD_LEN];
        for (int i = 0; i < PASSWORD_LEN; i++) {
            resultado[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
        }
        return new String(resultado);
    }

    private GeradorDeSenha() {
    }

}
