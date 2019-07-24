package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Leanderson Coelho
 * **/

@Entity
public class Administrador extends Usuario implements Serializable {

    public Administrador(String matricula, String senha, String email, String nome) {
        super(matricula, senha, email, nome);
    }

    public Administrador() {
    }
}
