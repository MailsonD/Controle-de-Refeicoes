package br.com.loopis.controle_refeicoes.modelo.excessoes;

public class ReadTokenException extends Exception {

    public ReadTokenException(){
        super("Deu erro na leitura");
    }
}
