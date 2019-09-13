package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.AcessoNegadoException;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.PaginaInvalidaExcpetion;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ServicePedido {
    
    @Inject
    private PedidoDao pedidoDao;
    
    public void salvar(Pedido p) throws AcessoNegadoException {
        if(p.getProfessor().getNivelAcesso().equals(NivelAcesso.PROFESSOR)){
            pedidoDao.salvar(p);
        }
        throw new AcessoNegadoException("O usuário que tentou realizar a solicitação não é um professor");
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
        if(!(pagina <= 0)){
            if(professor.getNivelAcesso().equals(NivelAcesso.PROFESSOR)){
                return pedidoDao.buscarPorProfessor(professor.getMatricula(), pagina);
            }else{
                throw new AcessoNegadoException("Somente um professor pode realizar esta operação");
            }
        }else{
            throw new PaginaInvalidaExcpetion("Não é possível enviar uma página menor que 1");
        }
    }
}
