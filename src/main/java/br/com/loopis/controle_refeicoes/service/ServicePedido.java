package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ServicePedido {
    
    @Inject
    private PedidoDao pedidoDao;
    
    public void salvar(Pedido p) throws MatriculaExistenteException {
        pedidoDao.salvar(p);
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
}
