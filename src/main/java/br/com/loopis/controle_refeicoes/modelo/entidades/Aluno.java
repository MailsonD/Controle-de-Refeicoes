package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Aluno implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String matricula;
    private String nome;
    @OneToOne(mappedBy = "alunoBeneficiado")
    private Beneficio beneficio;
}
