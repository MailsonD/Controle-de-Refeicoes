package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.conversor.DataConversor;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Leanderson Coelho
 * **/
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Usuario professor;
    @Lob
    private String justificativa;
    @Convert(converter = DataConversor.class)
    private LocalDate diaSolicitado;
    @Enumerated(EnumType.STRING)
    private Turma turma;
    @Enumerated(EnumType.STRING)
    private TipoBeneficio tipoBeneficio;
//    @ManyToMany(/*cascade = CascadeType.PERSIST*/)
    @ElementCollection
    @CollectionTable(name = "aluno_pedido")
    private List<Aluno> alunos;

    public Pedido(Long id, Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma, TipoBeneficio tipoBeneficio, List<Aluno> alunos) {
        this.id = id;
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = alunos;
    }

    public Pedido(Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma, TipoBeneficio tipoBeneficio, List<Aluno> alunos) {
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = alunos;
    }

    public Pedido(Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma, TipoBeneficio tipoBeneficio) {
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.tipoBeneficio = tipoBeneficio;
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TipoBeneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", professor=" + professor + ", justificativa=" + justificativa + ", diaSolicitado=" + diaSolicitado + ", turma=" + turma + ", tipoBeneficio=" + tipoBeneficio + ", alunos=" + alunos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.professor);
        hash = 59 * hash + Objects.hashCode(this.justificativa);
        hash = 59 * hash + Objects.hashCode(this.diaSolicitado);
        hash = 59 * hash + Objects.hashCode(this.turma);
        hash = 59 * hash + Objects.hashCode(this.tipoBeneficio);
        hash = 59 * hash + Objects.hashCode(this.alunos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.justificativa, other.justificativa)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        if (!Objects.equals(this.diaSolicitado, other.diaSolicitado)) {
            return false;
        }
        if (this.turma != other.turma) {
            return false;
        }
        if (this.tipoBeneficio != other.tipoBeneficio) {
            return false;
        }
        if (!Objects.equals(this.alunos, other.alunos)) {
            return false;
        }
        return true;
    }
    
    
}
