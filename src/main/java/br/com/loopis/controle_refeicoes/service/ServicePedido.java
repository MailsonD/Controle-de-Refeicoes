package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.StatusPedido;
import br.com.loopis.controle_refeicoes.modelo.excessoes.AcessoNegadoException;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.PaginaInvalidaExcpetion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import static br.com.loopis.controle_refeicoes.modelo.entidades.Pedido_.dataModificacaoDeStatus;
import static br.com.loopis.controle_refeicoes.modelo.entidades.Pedido_.professor;

@Stateless
public class ServicePedido {
    
    @Inject
    private PedidoDao pedidoDao;
    
    public void salvar(Pedido p) throws AcessoNegadoException {
        if(p.getProfessor().getNivelAcesso().equals(NivelAcesso.PROFESSOR)){
            pedidoDao.salvar(p);
        }else{
            throw new AcessoNegadoException("O usuário que tentou realizar a solicitação não é um professor");
        }
    }

    public void atualizar(Pedido p) {
        pedidoDao.atualizar(p);
    }

    public void remover(Pedido p) {
        pedidoDao.remover(p);
    }

    public Pedido buscar(Object key){
        return pedidoDao.buscar(key);
    }

    public List<Pedido> listar(){
        return pedidoDao.listar();
    }
    
    public Long totalRefeicoes(){
        return pedidoDao.quantidadeDeRefeicoes();
    }
    
    public List<Aluno> listDeAlunosAlmoco(){
        return pedidoDao.alunosQuePossuemBeneficio(LocalDate.now(), TipoBeneficio.ALMOCO);
    }
    
    public List<Aluno> listDeAlunosJanta(){
        return pedidoDao.alunosQuePossuemBeneficio(LocalDate.now(), TipoBeneficio.JANTA);
    }

    public List<Pedido> buscarPorProfessor(Usuario professor, int pagina) throws PaginaInvalidaExcpetion, AcessoNegadoException {
        if(validarPagina(pagina) && validarProfessor(professor)){

            return pedidoDao.buscarPorProfessor(professor.getMatricula(), pagina);

        }
        return new ArrayList<>();
    }

    public List<Pedido> buscarPorProfessor(Usuario professor, int pagina, LocalDate data, StatusPedido statusPedido) throws AcessoNegadoException, PaginaInvalidaExcpetion {
        if(validarPagina(pagina) && validarProfessor(professor)){

            return pedidoDao.buscarPedido(professor.getMatricula(), data, statusPedido, pagina);

        }
        return new ArrayList<>();
    }

    public List<Pedido> buscarPorProfessor(Usuario professor, int pagina, StatusPedido statusPedido) throws AcessoNegadoException, PaginaInvalidaExcpetion {
        if(validarPagina(pagina) && validarProfessor(professor)){

            return pedidoDao.buscarPorStatusPedido(professor.getMatricula(), statusPedido, pagina);

        }
        return new ArrayList<>();
    }

    public List<Pedido> buscarPorProfessor(Usuario professor, int pagina, LocalDate data) throws AcessoNegadoException, PaginaInvalidaExcpetion {
        if(validarPagina(pagina) && validarProfessor(professor)){

            return pedidoDao.buscarPorData(professor.getMatricula(), data, pagina);

        }
        return new ArrayList<>();
    }

    private boolean validarPagina(int pagina) throws PaginaInvalidaExcpetion {
        if ((pagina <= 0)) {
            throw new PaginaInvalidaExcpetion("Não é possível enviar uma página menor que 1");
        }
        return true;

    }

    private boolean validarProfessor(Usuario professor) throws AcessoNegadoException {
        if(!professor.getNivelAcesso().equals(NivelAcesso.PROFESSOR)){
            throw new AcessoNegadoException("Somente um professor pode realizar esta operação");
        }
        return true;
    }
}
