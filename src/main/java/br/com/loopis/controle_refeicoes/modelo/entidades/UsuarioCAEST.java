package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class UsuarioCAEST extends Usuario implements Serializable {
    public UsuarioCAEST(String matricula, String senha, String email, String nome) {
        super(matricula, senha, email, nome);
    }

    public UsuarioCAEST() {
    }
}
