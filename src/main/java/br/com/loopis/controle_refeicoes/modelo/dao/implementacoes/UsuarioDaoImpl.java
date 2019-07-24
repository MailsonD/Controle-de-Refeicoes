package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Usuario object) {
        em.persist(object);
    }

    @Override
    public void atualizar(Usuario object) {
        em.merge(object);
    }

    @Override
    public void remover(Usuario object) {
        em.remove(em.merge(object));
    }

    @Override
    public Usuario buscar(Object key) {
        return em.find(Usuario.class, key);
    }

    @Override
    public List<Usuario> listar() {
        return em.createQuery("SELECT u FROM Usuario u",Usuario.class).getResultList();

    }

    public <T> List<T> listar(Class<T> classe) {
        return em.createQuery("SELECT u FROM "+classe.getSimpleName()+" u",classe).getResultList();
    }
}
