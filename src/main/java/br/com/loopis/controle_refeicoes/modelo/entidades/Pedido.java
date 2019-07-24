package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

public class Pedido {

    @Id
    private int id;
    private Usuario professor;
    private String justificativa;
    private LocalDate diaSolicitado;
    private Turma turma;

    public Pedido(Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma) {
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
    }

    public Pedido(int id, Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma) {
        this(professor,justificativa,diaSolicitado,turma);
        this.id = id;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public LocalDate getDiaSolicitado() {
        return diaSolicitado;
    }

    public void setDiaSolicitado(LocalDate diaSolicitado) {
        this.diaSolicitado = diaSolicitado;
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
        Pedido pedido = (Pedido) o;
        return id == pedido.id &&
                Objects.equals(professor, pedido.professor) &&
                Objects.equals(justificativa, pedido.justificativa) &&
                Objects.equals(diaSolicitado, pedido.diaSolicitado) &&
                turma == pedido.turma;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professor, justificativa, diaSolicitado, turma);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", professor=" + professor +
                ", justificativa='" + justificativa + '\'' +
                ", diaSolicitado=" + diaSolicitado +
                ", turma=" + turma +
                '}';
    }
}
