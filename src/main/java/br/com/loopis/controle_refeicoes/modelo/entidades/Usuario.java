package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Leanderson Coelho
 * **/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario implements Serializable {

    @Id
    private String matricula;
    private String senha;
    private String email;
    private String nome;

    public Usuario(String matricula, String senha, String email, String nome) {
        this.matricula = matricula;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
    }

    public Usuario() {

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(matricula, usuario.matricula) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, senha, email, nome);
    }
}
