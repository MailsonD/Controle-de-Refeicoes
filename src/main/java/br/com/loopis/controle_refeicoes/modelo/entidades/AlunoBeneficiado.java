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
public class AlunoBeneficiado implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String editalBeneficio;
    @Enumerated(EnumType.STRING)
    private TipoBeneficio tipoBeneficio;
    @Column(unique = true)
    private String matricula;
    private String nome;

    public AlunoBeneficiado() {
    }

    public AlunoBeneficiado(String matricula, String nome, TipoBeneficio tipoBeneficio, String editalBeneficio) {
        this.editalBeneficio = editalBeneficio;
        this.tipoBeneficio = tipoBeneficio;
        this.matricula = matricula;
        this.nome = nome;
    }

    public AlunoBeneficiado(int id, String matricula, String nome, TipoBeneficio tipoBeneficio, String editalBeneficio) {
        this.id = id;
        this.editalBeneficio = editalBeneficio;
        this.tipoBeneficio = tipoBeneficio;
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoBeneficio getTipoBeneficio() {
        return tipoBeneficio;
    }

    public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
        this.tipoBeneficio = tipoBeneficio;
    }

    public String getEditalBeneficio() {
        return editalBeneficio;
    }

    public void setEditalBeneficio(String editalBeneficio) {
        this.editalBeneficio = editalBeneficio;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.editalBeneficio);
        hash = 71 * hash + Objects.hashCode(this.tipoBeneficio);
        hash = 71 * hash + Objects.hashCode(this.matricula);
        hash = 71 * hash + Objects.hashCode(this.nome);
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
        final AlunoBeneficiado other = (AlunoBeneficiado) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.editalBeneficio, other.editalBeneficio)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.tipoBeneficio != other.tipoBeneficio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AlunoBeneficiado{" + "id=" + id + ", editalBeneficio=" + editalBeneficio + ", tipoBeneficio=" + tipoBeneficio + ", matricula=" + matricula + ", nome=" + nome + '}';
    }

    
}
