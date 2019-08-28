/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ian
 */

@Named
@ViewScoped
public class PedidoBean {
    private Pedido pedido;
    private List<Pedido> pedidos;
    private Aluno aluno;
    private List<Aluno> alunos;
    @Inject
    private PedidoDao pedidoService;
    @Inject 
    private AlunoDao alunoService;
    
    @PostConstruct
    public void init(){
        pedido = new Pedido();
        pedidos = new ArrayList<>();
    }
    
    public String addAluno(){
        alunos.add(aluno);
        return null;
    }
}
