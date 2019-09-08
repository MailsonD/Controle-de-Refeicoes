package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Leanderson Coelho
 * **/

@Entity
public class Usuario implements Serializable {

//    @GeneratedValue
//    @Column(unique = true)
//    private int id;
    @Id
    private String matricula;
    private String senha;
    @Column(unique = true)
    private String email;
    private String nome;
    @Enumerated(EnumType.STRING)
    private NivelAcesso nivelAcesso;
    private Boolean ativo;

    public Usuario(String matricula, String senha, String email, String nome, NivelAcesso nivelAcesso, Boolean ativo) {
        this.matricula = matricula;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.ativo = ativo;
    }
    
    public Usuario(String matricula, String senha, String email, String nome, NivelAcesso nivelAcesso) {
        this.matricula = matricula;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.ativo = true;
    }
    
    public Usuario(String matricula, String email, String nome, NivelAcesso nivelAcesso) {
        this.matricula = matricula;
        this.email = email;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.ativo = true;
    }

    public Usuario() {
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return /*id == usuario.id &&*/
                Objects.equals(matricula, usuario.matricula) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(nome, usuario.nome) &&
                nivelAcesso == usuario.nivelAcesso &&
                Objects.equals(ativo, usuario.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, senha, email, nome, nivelAcesso, ativo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
//                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", nivelAcesso=" + nivelAcesso +
                ", ativo=" + ativo +
                '}';
    }
}
