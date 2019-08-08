package br.com.loopis.controle_refeicoes.modelo.dao.implementacoes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.validation.ConstraintViolationException;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.postgresql.util.PSQLException;
import javax.ejb.TransactionRolledbackLocalException;
import javax.persistence.PersistenceException;

/**
 * @author Mailson Dennis
 * @author Leanderson Coelho
 **/
@Stateless
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Usuario object) throws MatriculaExistenteException {
        try {
            object.setAtivo(true);
            em.persist(object);
        } catch (EntityExistsException e){
            throw new MatriculaExistenteException();
        }  
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

    @Override
    public List<Usuario> usuariosComNivelDeAcesso(NivelAcesso nivelAcesso) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nivelAcesso=:na", Usuario.class);
        query.setParameter("na", nivelAcesso);
        return query.getResultList();
    }

    @Override
    public void removerProfessores() {
        String jpql = "DELETE FROM Usuario u WHERE u.nivelAcesso=:na";
        Query query = em.createQuery(jpql);
        query.setParameter("na", NivelAcesso.PROFESSOR);
        query.executeUpdate();
    }

}

