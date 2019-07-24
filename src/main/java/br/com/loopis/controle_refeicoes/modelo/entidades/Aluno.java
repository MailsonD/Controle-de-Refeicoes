package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Id;

public class Aluno {

    @Id
    private int id;
    private String matricula;
    private String nome;
}
