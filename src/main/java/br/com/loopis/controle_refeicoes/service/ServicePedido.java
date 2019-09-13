package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.AcessoNegadoException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ServicePedido {
    
    @Inject
    private PedidoDao pedidoDao;
    
    public void salvar(Pedido p) throws MatriculaExistenteException, AcessoNegadoException {
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
}
