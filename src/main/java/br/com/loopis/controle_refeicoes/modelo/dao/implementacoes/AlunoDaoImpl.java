package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author
 * **/
@Stateless
public class AlunoDaoImpl implements AlunoDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void salvar(Aluno object) {
        em.persist(object);
    }

    @Override
    public void atualizar(Aluno object) {
        em.merge(object);
    }

    @Override
    public void remover(Aluno object) {
        em.remove(em.merge(object));
    }

    @Override
    public Aluno buscar(Object key) {
        return em.find(Aluno.class, key);
    }

    @Override
    public List<Aluno> listar() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return em.find(Aluno.class, matricula);
    }
}
