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
import java.util.ArrayList;
import java.util.Arrays;
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
 *
 */
@Stateless
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Usuario object) {
        try {
            object.setAtivo(true);
            em.persist(object);
        } catch (EntityExistsException e) {
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
        return em.createQuery("SELECT u FROM Usuario u where u.ativo=true", Usuario.class).getResultList();
    }

    @Override
    public Usuario buscarPorMatricula(String matricula) throws UsuarioNaoEncontradoException {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.matricula=:mat and u.ativo=true", Usuario.class);
        query.setParameter("mat", matricula);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    public Usuario autenticar(Usuario usuario) throws SenhaInvalidaException, UsuarioNaoEncontradoException {
        Usuario trueUser = this.buscarPorMatricula(usuario.getMatricula());
        if (usuario.getSenha().equals(trueUser.getSenha()) && trueUser.getAtivo()) {
            trueUser.setSenha(null);
            return trueUser;
        } else {
            throw new SenhaInvalidaException();
        }
    }

    @Override
    public List<Usuario> usuariosComNivelDeAcesso(NivelAcesso nivelAcesso) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nivelAcesso=:na and u.ativo=true", Usuario.class);
        query.setParameter("na", nivelAcesso);
        return query.getResultList();
    }

    @Override
    public void salvarProfessores(List<Usuario> professores) {
        List<Usuario> professoresPersistidos = em.createQuery("select p from Usuario p where p.nivelAcesso=:na").setParameter("na", NivelAcesso.PROFESSOR).getResultList();
        List<Usuario> auxProfessoresPersistidos = new ArrayList<>(professoresPersistidos);
        boolean excluiu;
        
        //todos os professores que serão atualizados
        for(Usuario professor:professores){
            excluiu = professoresPersistidos.removeIf(p->{
                return p.getMatricula().equals(professor.getMatricula());
            });
            if(excluiu){
                em.merge(professor);
            }
        }
        
        //todos os professores que terão as suas contas desativadas
        professoresPersistidos.forEach(p -> {
            p.setAtivo(false);
            em.merge(p);
        });
        
        //todos os novos professores que serão persistidos
        for(Usuario professor:auxProfessoresPersistidos){
            professores.removeIf(p->{
                return p.getMatricula().equals(professor.getMatricula());
            });
        }
        professores.forEach(p -> {
            em.persist(p);
        });        
    }

    @Override
    public void removerProfessor(Usuario u) {
        u.setAtivo(false);
        em.merge(u);
    }

}
