package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
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
        Usuario a = new Usuario("1234","123","meuEmail1","meu nome 1", NivelAcesso.ADMINISTRADOR,true);
        Usuario b = new Usuario("1235","123","meuEmail2","meu nome 2", NivelAcesso.ADMINISTRADOR,true);
        Usuario c = new Usuario("1236","123","meuEmail3","meu nome 3", NivelAcesso.ADMINISTRADOR,true);
        Usuario d = new Usuario("1237","123","meuEmail4","meu nome 4", NivelAcesso.ADMINISTRADOR,true);
        Usuario e = new Usuario("1238","123","meuEmail5","meu nome 5", NivelAcesso.GESTOR,true);
        try {
            usuarioDao.salvar(a);
            usuarioDao.salvar(b);
            usuarioDao.salvar(c);
            usuarioDao.salvar(d);
            usuarioDao.salvar(e);
        } catch (MatriculaExistenteException ex) {
            ex.printStackTrace();
        }

        
        //So vai exibir => "meu nome 5"

    }


}
