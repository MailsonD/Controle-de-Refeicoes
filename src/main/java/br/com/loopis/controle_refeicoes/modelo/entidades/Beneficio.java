package br.com.loopis.controle_refeicoes.modelo.entidades;


import java.io.Serializable;
import javax.persistence.Id;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

public class Beneficio implements Serializable{

    @Id
    private int id;
    private TipoBeneficio tipobeneficio;
    private String edital;

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
