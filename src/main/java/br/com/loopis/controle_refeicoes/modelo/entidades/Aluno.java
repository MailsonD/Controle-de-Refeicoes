package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Leanderson Coelho
 * *
 */
@Embeddable
public class Aluno implements Serializable {
    
    private String matricula;
    private String nome;

    public Aluno() {
    }

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Aluno{" + ", matricula=" + matricula + ", nome=" + nome + '}';
    }

}
