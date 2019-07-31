package br.com.loopis.controle_refeicoes.modelo.excessoes;

public class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException(){
        super("Senha incorreta para o usuário informado");
    }
}
