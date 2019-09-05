package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;

import java.util.Objects;

public class AlunoExibicao {

    private String matricula;
    private String nome;
    private Turma turma;

    public AlunoExibicao() {
    }

    public AlunoExibicao(String matricula, String nome, Turma turma) {
        this.matricula = matricula;
        this.nome = nome;
        this.turma = turma;
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoExibicao that = (AlunoExibicao) o;
        return Objects.equals(matricula, that.matricula) &&
                Objects.equals(nome, that.nome) &&
                turma == that.turma;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, nome, turma);
    }

    @Override
    public String toString() {
        return "AlunoExibicao{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", turma=" + turma +
                '}';
    }
}
