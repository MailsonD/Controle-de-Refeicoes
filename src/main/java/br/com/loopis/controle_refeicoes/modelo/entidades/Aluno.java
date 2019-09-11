package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Leanderson Coelho
 * *
 */
//@Embeddable //não funciona corretamente no eclipse link. JPA gera NullPointerException;
@Entity
public class Aluno implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    @Override
    public String toString() {
        return "Aluno{matricula=" + matricula + ", nome=" + nome + '}';
    }

}
