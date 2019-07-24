package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.persistence.*;
import java.io.Serializable;

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

}
