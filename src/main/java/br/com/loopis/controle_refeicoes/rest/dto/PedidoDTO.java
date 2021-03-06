package br.com.loopis.controle_refeicoes.rest.dto;


import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.JustificativaCAEST;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author mailson
 * @mail mailssondennis@gmail.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PedidoDTO implements Serializable {

    private Long id;
    @XmlElement(required = true)
    private String matriculaProfessor;
    @XmlElement(required = true)
    private String justificativa;
    @XmlElement(required = true)
    private LocalDate diaSolicitado;
    @XmlElement(required = true)
    private Turma turma;
    @XmlElement(required = true)
    private TipoBeneficio tipoBeneficio;
    @XmlList
    @XmlElement(required = true)
    private List<Aluno> alunos;
    private StatusPedido statusPedido;
    private JustificativaCAESTDTO justificativaCAEST;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, String matriculaProfessor, String justificativa, LocalDate diaSolicitado, Turma turma, TipoBeneficio tipoBeneficio, List<Aluno> alunos, StatusPedido statusPedido, JustificativaCAESTDTO justificativaCAEST) {
        this.id = id;
        this.matriculaProfessor = matriculaProfessor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = alunos;
        this.statusPedido = statusPedido;
        this.justificativaCAEST = justificativaCAEST;
    }


    public PedidoDTO(String matriculaProfessor, String justificativa, LocalDate diaSolicitado, Turma turma, TipoBeneficio tipoBeneficio, List<Aluno> alunos, StatusPedido statusPedido, JustificativaCAESTDTO justificativaCAEST) {
        this.matriculaProfessor = matriculaProfessor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = alunos;
        this.statusPedido = statusPedido;
        this.justificativaCAEST = justificativaCAEST;
    }

    public String getMatriculaProfessor() {
        return matriculaProfessor;
    }

    public void setMatriculaProfessor(String matriculaProfessor) {
        this.matriculaProfessor = matriculaProfessor;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public JustificativaCAESTDTO getJustificativaCAEST() {
        return justificativaCAEST;
    }

    public void setJustificativaCAEST(JustificativaCAESTDTO justificativaCAEST) {
        this.justificativaCAEST = justificativaCAEST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDTO pedidoDTO = (PedidoDTO) o;
        return Objects.equals(id, pedidoDTO.id) &&
                Objects.equals(matriculaProfessor, pedidoDTO.matriculaProfessor) &&
                Objects.equals(justificativa, pedidoDTO.justificativa) &&
                Objects.equals(diaSolicitado, pedidoDTO.diaSolicitado) &&
                turma == pedidoDTO.turma &&
                tipoBeneficio == pedidoDTO.tipoBeneficio &&
                Objects.equals(alunos, pedidoDTO.alunos) &&
                statusPedido == pedidoDTO.statusPedido &&
                Objects.equals(justificativaCAEST, pedidoDTO.justificativaCAEST);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matriculaProfessor, justificativa, diaSolicitado, turma, tipoBeneficio, alunos, statusPedido, justificativaCAEST);
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id=" + id +
                ", matriculaProfessor='" + matriculaProfessor + '\'' +
                ", justificativa='" + justificativa + '\'' +
                ", diaSolicitado=" + diaSolicitado +
                ", turma=" + turma +
                ", tipoBeneficio=" + tipoBeneficio +
                ", alunos=" + alunos +
                ", statusPedido=" + statusPedido +
                ", justificativaCAEST=" + justificativaCAEST +
                '}';
    }
}
