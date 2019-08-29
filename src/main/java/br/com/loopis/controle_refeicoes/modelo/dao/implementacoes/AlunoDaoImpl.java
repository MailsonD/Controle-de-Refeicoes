package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author
 * **/
@Stateless
public class AlunoDaoImpl implements AlunoDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void salvar(AlunoBeneficiado object) {
        em.persist(object);
    }

    @Override
    public void atualizar(AlunoBeneficiado object) {
        em.merge(object);
    }

    @Override
    public void remover(AlunoBeneficiado object) {
        em.remove(em.merge(object));
    }

    @Override
    public AlunoBeneficiado buscar(Object key) {
        return em.find(AlunoBeneficiado.class, key);
    }

    @Override
    public List<AlunoBeneficiado> listar() {
        return em.createQuery("SELECT a FROM AlunoBeneficiado a", AlunoBeneficiado.class).getResultList();
    }

    @Override
    public AlunoBeneficiado buscarPorMatricula(String matricula) {
        try{
            return em.createQuery("select a from AlunoBeneficiado a where a.matricula like :matricula", AlunoBeneficiado.class).setParameter("matricula", matricula).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
