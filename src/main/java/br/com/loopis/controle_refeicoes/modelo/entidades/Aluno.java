package br.com.loopis.controle_refeicoes.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Id;

public class Aluno implements Serializable{

    @Id
    private int id;
    private String matricula;
    private String nome;

    public Aluno() {
    }

    public Aluno(int id, String matricula, String nome) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
    }

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Aluno{" + "id=" + id + ", matricula=" + matricula + ", nome=" + nome + '}';
    }
    
    
}
