package br.com.loopis.controle_refeicoes.controle.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.postgresql.util.PSQLException;

@Singleton
@Startup
public class TestBean {

    @Inject
    private UsuarioDao usuarioDao;

    @PostConstruct
    private void init(){
        Usuario u = new Usuario("123","123","meuEmail","meu nome", NivelAcesso.ADMINISTRADOR,true);
        try {
            usuarioDao.salvar(u);
        } catch (MatriculaExistenteException ex) {
            ex.getMessage();
        }
//        Usuario v = new Usuario("123","123","meuEmails","meu nome 2", NivelAcesso.ADMINISTRADOR,true);
//        try {
//            //try {
//            usuarioDao.salvar(v);
//            //} catch (PSQLException e){
//        } catch (MatriculaExistenteException ex) {
//            ex.getMessage();
//        }
            
        //}
        try {
            System.out.println(usuarioDao.buscarPorMatricula(u).getSenha());
//            Usuario z = u;
//            z.setSenha("1234");
//            System.out.println(usuarioDao.buscarPorMatricula(u).getSenha());
            Usuario aut = usuarioDao.autenticar(u);
            if(aut!=null){
                System.out.println("Autenticado");
            }else System.out.println("Nao autenticado");
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        } catch (SenhaInvalidaException e) {
            e.printStackTrace();
        }
    }


}
