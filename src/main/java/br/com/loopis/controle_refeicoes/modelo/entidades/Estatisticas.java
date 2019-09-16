/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.modelo.entidades;

import br.com.loopis.controle_refeicoes.modelo.entidades.enums.DiaDaSemana;

/**
 *
 * @author ian
 */
public class Estatisticas {
    private Long almocosDeferidos;
    private Long almocosIndeferidos;
    private Long jantaresDeferidos;
    private Long jantaresIndeferidos;
    private String professorQueMaisSolicitouAlmocos;
    private String professorQueMaisSolicitouJantares;
    private DiaDaSemana diaDaSemanaComMaisSolicitacoes; 
    private DiaDaSemana diaDaSemanaComMenosSolicitacoes;

    public Estatisticas(Long almocosDeferidos, Long almocosIndeferidos, Long jantaresDeferidos, Long jantaresIndeferidos, String professorQueMaisSolicitouAlmocos, String professorQueMaisSolicitouJantares, DiaDaSemana diaDaSemanaComMaisSolicitacoes, DiaDaSemana diaDaSemanaComMenosSolicitacoes) {
        this.almocosDeferidos = almocosDeferidos;
        this.almocosIndeferidos = almocosIndeferidos;
        this.jantaresDeferidos = jantaresDeferidos;
        this.jantaresIndeferidos = jantaresIndeferidos;
        this.professorQueMaisSolicitouAlmocos = professorQueMaisSolicitouAlmocos;
        this.professorQueMaisSolicitouJantares = professorQueMaisSolicitouJantares;
        this.diaDaSemanaComMaisSolicitacoes = diaDaSemanaComMaisSolicitacoes;
        this.diaDaSemanaComMenosSolicitacoes = diaDaSemanaComMenosSolicitacoes;
    }

    public Estatisticas() {
    }

    public Long getAlmocosDeferidos() {
        return almocosDeferidos;
    }

    public void setAlmocosDeferidos(Long almocosDeferidos) {
        this.almocosDeferidos = almocosDeferidos;
    }

    public Long getAlmocosIndeferidos() {
        return almocosIndeferidos;
    }

    public void setAlmocosIndeferidos(Long almocosIndeferidos) {
        this.almocosIndeferidos = almocosIndeferidos;
    }

    public Long getJantaresDeferidos() {
        return jantaresDeferidos;
    }

    public void setJantaresDeferidos(Long jantaresDeferidos) {
        this.jantaresDeferidos = jantaresDeferidos;
    }

    public Long getJantaresIndeferidos() {
        return jantaresIndeferidos;
    }

    public void setJantaresIndeferidos(Long jantaresIndeferidos) {
        this.jantaresIndeferidos = jantaresIndeferidos;
    }

    public String getProfessorQueMaisSolicitouAlmocos() {
        return professorQueMaisSolicitouAlmocos;
    }

    public void setProfessorQueMaisSolicitouAlmocos(String professorQueMaisSolicitouAlmocos) {
        this.professorQueMaisSolicitouAlmocos = professorQueMaisSolicitouAlmocos;
    }

    public String getProfessorQueMaisSolicitouJantares() {
        return professorQueMaisSolicitouJantares;
    }

    public void setProfessorQueMaisSolicitouJantares(String professorQueMaisSolicitouJantares) {
        this.professorQueMaisSolicitouJantares = professorQueMaisSolicitouJantares;
    }

    public DiaDaSemana getDiaDaSemanaComMaisSolicitacoes() {
        return diaDaSemanaComMaisSolicitacoes;
    }

    public void setDiaDaSemanaComMaisSolicitacoes(DiaDaSemana diaDaSemanaComMaisSolicitacoes) {
        this.diaDaSemanaComMaisSolicitacoes = diaDaSemanaComMaisSolicitacoes;
    }

    public DiaDaSemana getDiaDaSemanaComMenosSolicitacoes() {
        return diaDaSemanaComMenosSolicitacoes;
    }

    public void setDiaDaSemanaComMenosSolicitacoes(DiaDaSemana diaDaSemanaComMenosSolicitacoes) {
        this.diaDaSemanaComMenosSolicitacoes = diaDaSemanaComMenosSolicitacoes;
    }

    @Override
    public String toString() {
        return "Estatisticas{" + "almocosDeferidos=" + almocosDeferidos + ", almocosIndeferidos=" + almocosIndeferidos + ", jantaresDeferidos=" + jantaresDeferidos + ", jantaresIndeferidos=" + jantaresIndeferidos + ", professorQueMaisSolicitouAlmocos=" + professorQueMaisSolicitouAlmocos + ", professorQueMaisSolicitouJantares=" + professorQueMaisSolicitouJantares + ", diaDaSemanaComMaisSolicitacoes=" + diaDaSemanaComMaisSolicitacoes + ", diaDaSemanaComMenosSolicitacoes=" + diaDaSemanaComMenosSolicitacoes + '}';
    }

    
    
}
