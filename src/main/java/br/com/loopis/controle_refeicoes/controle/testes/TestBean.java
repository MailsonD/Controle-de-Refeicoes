package br.com.loopis.controle_refeicoes.controle.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class TestBean {

    @Inject
    private UsuarioDao usuarioDao;

    @PostConstruct
    private void init(){
        Usuario u = new Usuario("123","123","meuEmail","meu nome", NivelAcesso.ADMINISTRADOR,true);
        usuarioDao.salvar(u);
        try {
            System.out.println(usuarioDao.buscarPorMatricula(u).getNome());
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
