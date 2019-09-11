package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.conversor.DataConversor;
import br.com.loopis.controle_refeicoes.modelo.conversor.DataTimeConversor;
import br.com.loopis.controle_refeicoes.modelo.conversor.DataXmlBind;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Enumerated(EnumType.ORDINAL)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    private TipoBeneficio tipoBeneficio;

    @ManyToMany(cascade = CascadeType.ALL)
    @CollectionTable(name = "aluno_pedido")
    private List<Aluno> alunos;

    @OneToOne(mappedBy = "pedido")
    private JustificativaCAEST justificativaCAEST;

    private LocalDateTime dataModificacaoDeStatus;

    @Transient
    private String justificativaCaestString;

    public Pedido(){};

    public Pedido(Long id, Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma, StatusPedido statusPedido, TipoBeneficio tipoBeneficio, List<Aluno> alunos) {
        this.id = id;
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.statusPedido = statusPedido;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = alunos;
    }

    public Pedido(Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma, StatusPedido statusPedido, TipoBeneficio tipoBeneficio, List<Aluno> alunos) {
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.statusPedido = statusPedido;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = alunos;
    }

    public Pedido(Usuario professor, String justificativa, LocalDate diaSolicitado, Turma turma, StatusPedido statusPedido, TipoBeneficio tipoBeneficio) {
        this.professor = professor;
        this.justificativa = justificativa;
        this.diaSolicitado = diaSolicitado;
        this.turma = turma;
        this.statusPedido = statusPedido;
        this.tipoBeneficio = tipoBeneficio;
        this.alunos = new ArrayList<>();
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

    public String getStatusPedido() {
        return String.valueOf(statusPedido);
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
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
    
    public int getQuantAlunos(){
        return alunos.size();
    }

    public JustificativaCAEST getJustificativaCAEST() {
        return justificativaCAEST;
    }

    public void setJustificativaCAEST(JustificativaCAEST justificativaCAEST) {
        this.justificativaCAEST = justificativaCAEST;
    }

    public String getJustificativaCaestString() {
        return justificativaCaestString;
    }

    public void setJustificativaCaestString(String justificativaCaestString) {
        this.justificativaCaestString = justificativaCaestString;
    }

    public LocalDateTime getDataModificacaoDeStatus() {
        return dataModificacaoDeStatus;
    }

    public void setDataModificacaoDeStatus(LocalDateTime dataModificacaoDeStatus) {
        this.dataModificacaoDeStatus = dataModificacaoDeStatus;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id) &&
                professor.equals(pedido.professor) &&
                justificativa.equals(pedido.justificativa) &&
                diaSolicitado.equals(pedido.diaSolicitado) &&
                turma == pedido.turma &&
                statusPedido == pedido.statusPedido &&
                tipoBeneficio == pedido.tipoBeneficio &&
                alunos.equals(pedido.alunos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professor, justificativa, diaSolicitado, turma, statusPedido, tipoBeneficio, alunos);
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", professor=" + professor + ", justificativa=" + justificativa + ", diaSolicitado=" + diaSolicitado + ", turma=" + turma + ", statusPedido=" + statusPedido + ", tipoBeneficio=" + tipoBeneficio + ", alunos=" + alunos + '}';
    }
    
    
}
