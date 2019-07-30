package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mailson Dennis
 * @author Leanderson Coelho
 **/
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
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    @Override
    public Usuario buscarPorMatricula(Usuario usuario) throws UsuarioNaoEncontradoException {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.matricula=:mat", Usuario.class);
        query.setParameter("mat", usuario.getMatricula());
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    public Usuario autenticar(Usuario usuario) throws SenhaInvalidaException, UsuarioNaoEncontradoException {
        Usuario trueUser = this.buscarPorMatricula(usuario);
        if (usuario.getSenha().equals(trueUser.getSenha()) && trueUser.getAtivo()) {
            return trueUser;
        } else throw new SenhaInvalidaException();
    }

}

