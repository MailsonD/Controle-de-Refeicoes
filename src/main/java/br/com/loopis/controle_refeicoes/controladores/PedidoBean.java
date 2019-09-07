/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.JustificativaCAESTDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;
import br.com.loopis.controle_refeicoes.modelo.entidades.Estatisticas;
import br.com.loopis.controle_refeicoes.modelo.entidades.JustificativaCAEST;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.DiaDaSemana;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.Turma;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class PedidoBean implements Serializable {
    
    public final static int QUANTIDADE_POR_PAGINA = 10;
    
    private Pedido pedido;
    private Aluno aluno;
    private List<Aluno> alunos;
    private List<TipoBeneficio> tipoBeneficiosSelecionados;
    private List<Pedido> pedidos;
    private Long countPedidosAceitos;
    private Long countPedidosPendentes;
    private Long countPedidosRecusados;
    private Long quantRefeicoes;
    private int numPagina;
    private boolean ehUltimaPagina;
    private LocalDate dia;
    private StatusPedido statusPedido;
    private JustificativaCAEST justificativaCAEST;
    private Estatisticas estatisticas;

    @Inject
    private JustificativaCAESTDao justificativaCAESTService;
    @Inject
    private PedidoDao pedidoService;
    @Inject
    private AlunoDao alunoService;

    @PostConstruct
    public void init() {
        aluno = new Aluno();
        alunos = new ArrayList<>();
        tipoBeneficiosSelecionados = new ArrayList<>();
        pedido = new Pedido();
        numPagina = 1;
        justificativaCAEST = new JustificativaCAEST();
        dia = LocalDate.now();
        contagemDePedidosPorStatus();
        statusPedido = null;
        ehUltimaPagina = false;
        pedidos = new ArrayList<>();
        estatisticas = new Estatisticas();
    }

    public String addAluno() {
        if (aluno.getMatricula().equals("") || aluno.getNome().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "•Informe nome e matrícula do aluno", null));
            return null;
        }

        for (Aluno a : alunos) {
            if (a.getMatricula().equals(aluno.getMatricula())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "•Aluno com matrícula " + aluno.getMatricula() + " já foi adicionado à lista", null));
                return null;
            }
        }

        alunos.add(aluno);
        aluno = new Aluno();
        return null;
    }


    public String cadastrarPedido() {
        int tamList = alunos.size();
        if (pedido.getDiaSolicitado().isBefore(LocalDate.now()) || 
                pedido.getDiaSolicitado().equals(LocalDate.now()) || 
                pedido.getDiaSolicitado().getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                pedido.getDiaSolicitado().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "•Você não pode fazer uma solicitação de refeição nesta data", null));
            return null;
        } 
        alunos.removeIf(a -> {
            AlunoBeneficiado alunoBeneficiado = alunoService.buscarPorMatricula(a.getMatricula());
            if (alunoBeneficiado != null) {
                TipoBeneficio beneficioAlunoBeneficio = alunoBeneficiado.getTipoBeneficio();
                if (beneficioAlunoBeneficio == pedido.getTipoBeneficio() || beneficioAlunoBeneficio == TipoBeneficio.AMBOS) {
                    return true;
                }
            }
            return false;
        });

        if (tamList != alunos.size()) {
            if (alunos.size() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "•Todos os alunos selecionados já possuem o benefício", null));
                return null;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "•Na lista havia alunos que já recebem o benefício. Eles serão removidos da lista para você", null));
        }
        if (tipoBeneficiosSelecionados.size() > 1) {
            pedido.setTipoBeneficio(TipoBeneficio.AMBOS);
        } else {
            pedido.setTipoBeneficio(tipoBeneficiosSelecionados.get(0));
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        pedido.setProfessor((Usuario) session.getAttribute("usuarioLogado"));
        pedido.setAlunos(alunos);
        pedido.setStatusPedido(StatusPedido.PENDENTE);
        pedidoService.salvar(pedido);
        pedidoService.agendaModificacaoPedido(pedido);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "•Solicitação realizado com sucesso!", null));
        tipoBeneficiosSelecionados = new ArrayList<>();
        alunos = new ArrayList<>();
        aluno = new Aluno();
        pedido = new Pedido();
        return null;
    }

    public String excluir(Pedido p) {
        pedidoService.remover(p);
        pedido = new Pedido();
        return null;
    }
    
    public TipoBeneficio[] getTiposBeneficio() {
        return TipoBeneficio.values();
    }

    public int tamanhoListaAlunos() {
        return alunos.size();
    }

    public String contagemDePedidosPorStatus() {
        if (dia == null) {
            dia = LocalDate.now();
        }
        countPedidosPendentes = pedidoService.quantidadeDePedidosPorStatus(dia, StatusPedido.PENDENTE);
        countPedidosAceitos = pedidoService.quantidadeDePedidosPorStatus(dia, StatusPedido.ACEITO);
        countPedidosRecusados = pedidoService.quantidadeDePedidosPorStatus(dia, StatusPedido.RECUSADO);
        quantRefeicoes = pedidoService.quantidadeDeRefeicoes(dia);
        return null;

    }
    
    public String mudarPagina(int p){
        numPagina+=p;
        return null;
    }
    
    public List<Pedido> listar(String matriculaUsuario) {
        List<Pedido> ps;
        if(dia == null && statusPedido != null){
            ps = pedidoService.buscarPorStatusPedido(matriculaUsuario, statusPedido, numPagina);
        }
        else if(dia != null && statusPedido == null){
            ps = pedidoService.buscarPorData(matriculaUsuario, dia, numPagina);
        }
        else if(dia == null && statusPedido == null){
            ps = pedidoService.buscarPorProfessor(matriculaUsuario, numPagina);
        }
        else{
            ps = pedidoService.buscarPedido(matriculaUsuario, dia, statusPedido, numPagina);
        }
        verificaSeEhUltimaPagina(ps.size());
        return ps;
//        return pedidoService.buscarPorProfessor(matriculaUsuario, numPagina);
    }
    
    public List<Pedido> listarPendentes() {
        List<Pedido> ps;
        ps = pedidoService.buscarPorStatusPedido(StatusPedido.PENDENTE, numPagina);
        verificaSeEhUltimaPagina(ps.size());
        return ps;
    }
    
    public List<Pedido> listarAceitos() {
        List<Pedido> ps;
        ps = pedidoService.buscarPorStatusPedido(StatusPedido.ACEITO, numPagina);
        verificaSeEhUltimaPagina(ps.size());
        return ps;
    }
    
    public List<Pedido> listarRecusados() {
        List<Pedido> ps;
        ps = pedidoService.buscarPorStatusPedido(StatusPedido.RECUSADO, numPagina);
        verificaSeEhUltimaPagina(ps.size());
        return ps;
    }
    
    public List<Pedido> ultimosPedidosComStatusModificado(){
        List<Pedido> ps;
        ps = pedidoService.ultimosPedidosComStatusModificado(numPagina);
        verificaSeEhUltimaPagina(ps.size());
        return ps;
    }
    
    public void verificaSeEhUltimaPagina(int tamList){
        if(tamList<QUANTIDADE_POR_PAGINA){
            ehUltimaPagina = true;
        }else{
            ehUltimaPagina = false;
        }
    }
    
    public Turma[] listarTurmas() {
        return Turma.values();
    }
    
    public StatusPedido[] listarStatusPedidos(){
        return StatusPedido.values();
    }
    
    public String recarregar(){
        return null;
    }
    
    //aluno já esta em outras solicitações deferidas?
    public boolean alunoJaPossuiBeneficio(Aluno a, LocalDate diaPedido) {
        List<Aluno> alunosContemplados = pedidoService.alunosQuePossuemBeneficio(diaPedido);
        if (alunosContemplados.isEmpty()) {
            return false;
        }
        return alunosContemplados.stream().anyMatch((outroAluno) -> (outroAluno.getMatricula().equals(a.getMatricula())));
    }


    public String recusar(Pedido p, Usuario usuarioCaest) {
//        System.out.println("\n------------------>"+p.getJustificativaCaestString());
        p.setDataModificacaoDeStatus(LocalDateTime.now());
        justificativaCAEST.setPedido(p);
        justificativaCAEST.setUsuarioCAEST(usuarioCaest);
        justificativaCAEST.setJustificativa(p.getJustificativaCaestString());
        justificativaCAESTService.salvar(justificativaCAEST);
        justificativaCAEST = new JustificativaCAEST();
        contagemDePedidosPorStatus();
        return null;
    }

    public String aceitar(Pedido p) {
        p.setDataModificacaoDeStatus(LocalDateTime.now());
        p.setStatusPedido(StatusPedido.ACEITO);
        pedidoService.atualizar(p);
        contagemDePedidosPorStatus();
        return null;
    }
    
    
    
    public String gerarEstatisticas() {
        estatisticas.setAlmocosDeferidos(pedidoService.quantidadeDeRefeicoes(StatusPedido.ACEITO, TipoBeneficio.ALMOCO));
        estatisticas.setAlmocosIndeferidos(pedidoService.quantidadeDeRefeicoes(StatusPedido.RECUSADO, TipoBeneficio.ALMOCO));
        estatisticas.setJantaresDeferidos(pedidoService.quantidadeDeRefeicoes(StatusPedido.ACEITO, TipoBeneficio.JANTA));
        estatisticas.setJantaresIndeferidos(pedidoService.quantidadeDeRefeicoes(StatusPedido.RECUSADO, TipoBeneficio.JANTA));
        
        List<Object[]> tabela1 = pedidoService.rankingProfessoresQueMaisSolicitaramAlmoco(TipoBeneficio.ALMOCO);
        if(tabela1.size()>0){
            estatisticas.setProfessorQueMaisSolicitouAlmocos((String) tabela1.get(0)[0]);
        }else{
            estatisticas.setProfessorQueMaisSolicitouAlmocos("");
        }
        
        List<Object[]> tabela2 = pedidoService.rankingProfessoresQueMaisSolicitaramAlmoco(TipoBeneficio.JANTA);
        if(tabela1.size()>0){
            estatisticas.setProfessorQueMaisSolicitouJantares((String) tabela1.get(0)[0]);
        }else{
            estatisticas.setProfessorQueMaisSolicitouJantares("");
        }
        
        List<Object[]> tabela3 = pedidoService.rankingDiasComMaisSolicitacao();
        if(tabela3.size()>0){
            estatisticas.setDiaDaSemanaComMaisSolicitacoes(DiaDaSemana.valueOf((Integer)tabela3.get(0)[0]));
        }else{
            estatisticas.setDiaDaSemanaComMaisSolicitacoes(null);
        }
        
        if(tabela3.size()>1){
            estatisticas.setDiaDaSemanaComMenosSolicitacoes(DiaDaSemana.valueOf(
                    (Integer)tabela3.get(tabela3.size()-1)[0])
            );
        }else{
            estatisticas.setDiaDaSemanaComMenosSolicitacoes(null);
        }
        return null;
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

    public JustificativaCAEST getJustificativaCAEST() {
        return justificativaCAEST;
    }

    public void setJustificativaCAEST(JustificativaCAEST justificativaCAEST) {
        this.justificativaCAEST = justificativaCAEST;
    }

    public Long getCountPedidosAceitos() {
        return countPedidosAceitos;
    }

    public void setCountPedidosAceitos(Long countPedidosAceitos) {
        this.countPedidosAceitos = countPedidosAceitos;
    }

    public Long getCountPedidosPendentes() {
        return countPedidosPendentes;
    }

    public void setCountPedidosPendentes(Long countPedidosPendentes) {
        this.countPedidosPendentes = countPedidosPendentes;
    }

    public Long getCountPedidosRecusados() {
        return countPedidosRecusados;
    }

    public void setCountPedidosRecusados(Long countPedidosRecusados) {
        this.countPedidosRecusados = countPedidosRecusados;
    }

    public Long getQuantRefeicoes() {
        return quantRefeicoes;
    }

    public void setQuantRefeicoes(Long quantRefeicoes) {
        this.quantRefeicoes = quantRefeicoes;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public boolean isEhUltimaPagina() {
        return ehUltimaPagina;
    }

    public void setEhUltimaPagina(boolean ehUltimaPagina) {
        this.ehUltimaPagina = ehUltimaPagina;
    }

    public Estatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
    }
    
    

}
//deve ser permitido a persistencia de pedidos para o mesmo dia, de professores diferentes e com os mesmos alunos. <== para gerar estatisticas relacionadas a professores.
