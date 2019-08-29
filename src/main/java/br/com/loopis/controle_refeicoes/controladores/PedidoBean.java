/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ian
 */

@Named
@ViewScoped
public class PedidoBean implements Serializable{
    
    private Pedido pedido;
    private Aluno aluno;
    private List<Aluno> alunos;
    private List<TipoBeneficio> tipoBeneficiosSelecionados;
    @Inject
    private PedidoDao pedidoService;
    @Inject 
    private AlunoDao alunoService;
    
    @PostConstruct
    public void init(){
        aluno = new Aluno();
        alunos = new ArrayList<>();
        tipoBeneficiosSelecionados = new ArrayList<>();
        pedido = new Pedido();
    }
    
    public String addAluno(){
        alunos.add(aluno);
        aluno = new Aluno();
        return null;
    }
    
    public Turma[] listarTurmas(){
        return Turma.values();
    }
    
    public String cadastrarPedido(){
        int tamList = alunos.size();
        alunos.removeIf(a->{
            AlunoBeneficiado alunoBeneficiado = alunoService.buscarPorMatricula(a.getMatricula());
            if(alunoBeneficiado!=null){
                TipoBeneficio beneficioAlunoBeneficio = alunoBeneficiado.getTipoBeneficio();
                if(beneficioAlunoBeneficio==pedido.getTipoBeneficio() || beneficioAlunoBeneficio==TipoBeneficio.AMBOS){
                    return true;
                }
            }
            return false;
        });
        
        if(tamList!=alunos.size()){
            if(alunos.size()==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos os alunos selecionados já possuem o benefício", null));
                return null;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Na lista havia alunos que já recebem o benefício. Eles serão removidos da lista para você", null));
        }
        if(tipoBeneficiosSelecionados.size()>1){
            pedido.setTipoBeneficio(TipoBeneficio.AMBOS);
        }else{
            pedido.setTipoBeneficio(tipoBeneficiosSelecionados.get(0));
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        pedido.setProfessor((Usuario)session.getAttribute("usuarioLogado"));
        pedido.setAlunos(alunos);
        pedidoService.salvar(pedido);
        tipoBeneficiosSelecionados = new ArrayList<>();
        alunos = new ArrayList<>();
        aluno = new Aluno();
        pedido = new Pedido();
        return null;
    }
    
    public TipoBeneficio[] getTiposBeneficio(){
        return TipoBeneficio.values();
    }
    
    public int tamanhoListaAlunos(){
        return alunos.size();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<TipoBeneficio> getTipoBeneficiosSelecionados() {
        return tipoBeneficiosSelecionados;
    }

    public void setTipoBeneficiosSelecionados(List<TipoBeneficio> tipoBeneficiosSelecionados) {
        this.tipoBeneficiosSelecionados = tipoBeneficiosSelecionados;
    }

    
    
    
}
