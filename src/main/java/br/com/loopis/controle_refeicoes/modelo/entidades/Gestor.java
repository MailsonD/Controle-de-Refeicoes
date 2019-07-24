package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author Leanderson Coelho
 * **/

@Entity
public class Gestor extends Usuario implements Serializable {
    public Gestor(String matricula, String senha, String email, String nome) {
        super(matricula, senha, email, nome);
    }

    public Gestor() {
    }
}
