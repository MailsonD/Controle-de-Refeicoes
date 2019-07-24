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
//        Usuario user1 = new Administrador("12351","123123","Mailsondqweh","MAILASONDIQWHE");
//        Usuario user2 = new Professor("12451","123123","Mailsondqweh","MAasdasdas");
//        Usuario user3 = new UsuarioCAEST("12551","123123","Mailsondqweh","MasdasdaLASOsadWHE");
//        Usuario user4 = new Gestor("12651","123123","MASDHQWUEHWdqweh","DPAODSiyqeE");
//
//        usuarioDao.salvar(user1);
//        usuarioDao.salvar(user2);
//        usuarioDao.salvar(user3);
//        usuarioDao.salvar(user4);
//
//        usuarioDao.listar().forEach(
//                u -> System.out.println(u.getNome())
//        );

        Usuario u = usuarioDao.buscar("12351");
        System.out.println(u.getNome());
        u.setEmail("Leanderson");
        usuarioDao.atualizar(u);
        System.out.println(u.getNome());
        usuarioDao.remover(u);
    }


}
