package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PedidoDaoImpl implements PedidoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Pedido object) {
        em.persist(object);
    }

    @Override
    public void atualizar(Pedido object) {
        em.merge(object);
    }

    @Override
    public void remover(Pedido object) {
        em.remove(em.merge(object));
    }

    @Override
    public Pedido buscar(Object key){
        return em.find(Pedido.class,key);
    }

    @Override
    public List<Pedido> listar(){
        return em.createQuery("SELECT p FROM Pedido p").getResultList();
    }
}
