package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Leanderson Coelho
 * *
 */
@Entity
public class Beneficio implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String edital;
    @Enumerated(EnumType.STRING)
    private TipoBeneficio tipoBeneficio;
    @OneToOne()
    private Aluno alunoBeneficiado;

    public Beneficio() {
    }

    public Beneficio(TipoBeneficio tipoBeneficio, String edital) {
        this.edital = edital;
        this.tipoBeneficio = tipoBeneficio;
    }
    
    public Beneficio(String edital, TipoBeneficio tipoBeneficio, Aluno alunoBeneficiado) {
        this.edital = edital;
        this.tipoBeneficio = tipoBeneficio;
        this.alunoBeneficiado = alunoBeneficiado;
    }

    public Beneficio(int id, String edital, TipoBeneficio tipoBeneficio, Aluno alunoBeneficiado) {
        this.id = id;
        this.edital = edital;
        this.tipoBeneficio = tipoBeneficio;
        this.alunoBeneficiado = alunoBeneficiado;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public Aluno getAlunoBeneficiado() {
        return alunoBeneficiado;
    }

    public void setAlunoBeneficiado(Aluno alunoBeneficiado) {
        this.alunoBeneficiado = alunoBeneficiado;
    }

    public TipoBeneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Beneficio beneficio = (Beneficio) o;
        return id == beneficio.id
                && Objects.equals(edital, beneficio.edital)
                && tipoBeneficio == beneficio.tipoBeneficio
                && Objects.equals(alunoBeneficiado, beneficio.alunoBeneficiado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edital, tipoBeneficio, alunoBeneficiado);
    }

    @Override
    public String toString() {
        return "Beneficio{"
                + "id=" + id
                + ", edital='" + edital + '\''
                + ", tipoBeneficio=" + tipoBeneficio
                + ", alunoBeneficiado=" + alunoBeneficiado
                + '}';
    }
}
