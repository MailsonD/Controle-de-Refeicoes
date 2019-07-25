package br.com.loopis.controle_refeicoes.controle;
import java.io.*;
import java.util.Arrays;

public class ManipuladorCSV {
    private static final String VIRGULA = ",";
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/ian/Projetos_Programas/Java/Controle-de-Refeicoes/dataJul-25-2019.csv")));
        String linha = null;
        while ((linha = reader.readLine()) != null) {
            String[] dadosUsuario = linha.split(VIRGULA);
//          System.out.println(Arrays.toString(dadosUsuario));
            System.out.println("Teste1: " + dadosUsuario[0]);
            System.out.println("Teste2: " + dadosUsuario[1]);
            System.out.println("Teste3: " + dadosUsuario[2]);
            System.out.println("Teste4: " + dadosUsuario[3]);
            System.out.println("--------------------------");
        }
        reader.close();
    }
}
