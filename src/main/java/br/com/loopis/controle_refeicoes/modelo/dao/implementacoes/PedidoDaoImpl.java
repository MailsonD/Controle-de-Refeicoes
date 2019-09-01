package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.PedidoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Pedido;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Leanderson Coelho
 * **/
@Stateless
public class PedidoDaoImpl implements PedidoDao {

    public final static int QUANTIDADE_POR_PAGINA = 10;

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

    @Override
    public List<Pedido> buscarPorData(LocalDate data, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.diaSolicitado = :data ORDER BY p.diaSolicitado", Pedido.class);
        query.setParameter("data", data);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    @Override
    public List<Pedido> buscarPorProfessor(int keyProfessor, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.professor.id = :keyProfessor ORDER BY p.statusPedido", Pedido.class);
        query.setParameter("keyProfessor", keyProfessor);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }

    public List<Pedido> buscarPorTipoBeneficio(TipoBeneficio tipoBeneficio, int numeroDaPagina) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.tipoBeneficio = :tipoBeneficio ORDER BY p.diaSolicitado DESC", Pedido.class);
        query.setParameter("tipoBeneficio", tipoBeneficio);
        return query.setFirstResult(this.QUANTIDADE_POR_PAGINA * (numeroDaPagina - 1))
                .setMaxResults(this.QUANTIDADE_POR_PAGINA)
                .getResultList();
    }


}