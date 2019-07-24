package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Beneficio implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private TipoBeneficio tipobeneficio;
    private String edital;
    @Enumerated(EnumType.STRING)
    private TipoBeneficio tipoBeneficio;
    @OneToOne()
    private Aluno alunoBeneficiado;

    public Beneficio() {
    }

    public Beneficio(int id, TipoBeneficio tipobeneficio, String edital) {
        this.id = id;
        this.tipobeneficio = tipobeneficio;
        this.edital = edital;
    }

    public Beneficio(TipoBeneficio tipobeneficio, String edital) {
        this.tipobeneficio = tipobeneficio;
        this.edital = edital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoBeneficio getTipobeneficio() {
        return tipobeneficio;
    }

    public void setTipobeneficio(TipoBeneficio tipobeneficio) {
        this.tipobeneficio = tipobeneficio;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    @Override
    public String toString() {
        return "Beneficio{" + "id=" + id + ", tipobeneficio=" + tipobeneficio + ", edital=" + edital + '}';
    }
    
}
