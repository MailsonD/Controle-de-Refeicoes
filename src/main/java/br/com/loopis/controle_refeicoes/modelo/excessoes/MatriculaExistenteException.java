package br.com.loopis.controle_refeicoes.modelo.excessoes;

public class MatriculaExistenteException extends RuntimeException {

    public MatriculaExistenteException() {
        super("Matricula já existe");
    }
   
}
