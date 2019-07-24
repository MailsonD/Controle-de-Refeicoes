package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Leanderson Coelho
 * **/

@Entity
public class Usuario implements Serializable {

    @Id
    private int id;
    @Column(unique = true)
    private String matricula;
    private String senha;
    @Column(unique = true)
    private String email;
    private String nome;
    @Enumerated
    private NivelAcesso nivelAcesso;

    public Usuario(int id, String matricula, String senha, String email, String nome, NivelAcesso nivelAcesso) {
        this.id = id;
        this.matricula = matricula;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario() {
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

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id &&
                Objects.equals(matricula, usuario.matricula) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(nome, usuario.nome) &&
                nivelAcesso == usuario.nivelAcesso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, senha, email, nome, nivelAcesso);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", nivelAcesso=" + nivelAcesso +
                '}';
    }
}
