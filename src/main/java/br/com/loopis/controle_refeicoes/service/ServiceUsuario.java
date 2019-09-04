package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import java.util.List;
import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiceUsuario {
    
    @Inject
    private UsuarioDao usuarioDao;
    
    @Resource
    private SessionContext context;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void salvar(Usuario u) throws MatriculaExistenteException{
        try{
            usuarioDao.salvar(u);
        }catch(MatriculaExistenteException e){
            System.out.println("foi no matricula");
            context.setRollbackOnly();
        }
    };
    
    public void atualizar(Usuario u) {
        usuarioDao.atualizar(u);
    }

    public void remover(Usuario u) {
        usuarioDao.remover(u);
    }

    public Usuario buscar(Object key) {
        return usuarioDao.buscar(key);
    }

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public Usuario buscarPorMatricula(String matricula) throws UsuarioNaoEncontradoException {
        return usuarioDao.buscarPorMatricula(matricula);
    }

    public Usuario autenticar(Usuario usuario) throws SenhaInvalidaException, UsuarioNaoEncontradoException {
        return usuarioDao.autenticar(usuario);
    }

    public List<Usuario> usuariosComNivelDeAcesso(NivelAcesso nivelAcesso) {
        return usuarioDao.usuariosComNivelDeAcesso(NivelAcesso.PROFESSOR);
    }

}
