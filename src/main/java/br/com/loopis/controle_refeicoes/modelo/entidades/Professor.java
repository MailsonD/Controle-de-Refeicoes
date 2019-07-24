package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author Leanderson Coelho
 * **/

@Entity
public class Professor extends Usuario implements Serializable {

    public Professor(String matricula, String senha, String email, String nome) {
        super(matricula, senha, email, nome);
    }

    public Professor() {
    }
}
