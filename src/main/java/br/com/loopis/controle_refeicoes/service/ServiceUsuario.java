package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.*;
import br.com.loopis.controle_refeicoes.util.GeradorDeSenha;
import br.com.loopis.controle_refeicoes.util.MailUtil;

import java.util.List;
import java.util.logging.Logger;
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
    private final Logger log = Logger.getLogger(ServiceUsuario.class.getName());

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
        Usuario user = usuarioDao.buscarPorMatricula(matricula);
        return user;
    }

    public Usuario autenticar(Usuario usuario) throws SenhaInvalidaException, UsuarioNaoEncontradoException {
        return usuarioDao.autenticar(usuario);
    }

    public List<Usuario> usuariosComNivelDeAcesso(NivelAcesso nivelAcesso) {
        return usuarioDao.usuariosComNivelDeAcesso(NivelAcesso.PROFESSOR);
    }

    public void alterarSenha(String matricula, String senhaAntiga, String senhaNova) throws UsuarioNaoEncontradoException, SenhaInvalidaException {
        Usuario user = usuarioDao.buscarPorMatricula(matricula);
        if(user.getSenha().equals(senhaAntiga)){
            user.setSenha(senhaNova);
            usuarioDao.atualizar(user);
        }else{
            throw new SenhaInvalidaException();
        }

    }


    public void primeiroAcesso(String matricula, String email) throws UsuarioNaoEncontradoException, SenhaExistenteException, EmailInvalidoException {
        Usuario user = usuarioDao.buscarPorMatricula(matricula);
        if(user.getSenha() != null && !user.getSenha().isEmpty()){
            throw new SenhaExistenteException("Este não é o primeiro acesso deste usuário");
        }else if(user.getEmail().equals(email)) {
            log.info("gerando a senha");
            String senhaGerada = GeradorDeSenha.gerarSenhaAleatoria();
            user.setSenha(senhaGerada);
            usuarioDao.atualizar(user);
            log.info("senha gerada");
            log.info("Enviando email");
            MailUtil.enviarEmail(email,user.getNome(),senhaGerada);
            log.info("processo de primeiro acesso concluído");
        }else {
            throw new EmailInvalidoException("Este não é seu email de acesso!");
        }


    }
}
