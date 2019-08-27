package br.com.loopis.controle_refeicoes.modelo.excessoes;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class MatriculaExistenteException extends Exception {

    public MatriculaExistenteException() {
        super("Matricula já existe");
    }
   
}
