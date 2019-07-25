package br.com.loopis.controle_refeicoes.modelo.excessoes;

public class UsuarioNaoEncontradoException extends Exception{

    public UsuarioNaoEncontradoException(){
        super("Usuario inexistente");
    }

}
