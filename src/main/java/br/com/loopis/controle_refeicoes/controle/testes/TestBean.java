package br.com.loopis.controle_refeicoes.controle.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;

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
    }


}
