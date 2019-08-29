package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.service.ServiceUsuario;
import br.com.loopis.controle_refeicoes.util.ManipuladorCSV;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class TestBean {

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private AlunoDao alunoDao;
    @Inject
    private ServiceUsuario serviceUsuario;

    @PostConstruct
    private void init() {
        Usuario u1 = new Usuario("1", "1", "1", "Usuario1", NivelAcesso.PROFESSOR, Boolean.TRUE);
        Usuario u2 = new Usuario("2", "2", "2", "Usuario2", NivelAcesso.ADMINISTRADOR, Boolean.TRUE);
        Usuario u3 = new Usuario("3", "3", "3", "Usuario3", NivelAcesso.CAEST, Boolean.TRUE);
        Usuario u4 = new Usuario("4", "4", "4", "Usuario4", NivelAcesso.GESTOR, Boolean.TRUE);
        Usuario u5 = new Usuario("123", null, "mailssondennis@gmail.com", "Mailson", NivelAcesso.PROFESSOR, true);
        try {
            usuarioDao.salvar(u1);
            usuarioDao.salvar(u2);
            usuarioDao.salvar(u3);
            usuarioDao.salvar(u4);
            usuarioDao.salvar(u5);
        } catch (MatriculaExistenteException ex) {
            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            List<Usuario> l = new ArrayList<>();
//            l.add(new Usuario("123", "321", "email@gmail.com", "Zé", NivelAcesso.ADMINISTRADOR));
//            File f = ManipuladorCSV.toProfessorCsv(l);
//            System.out.println("\n" + f.getAbsolutePath());
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }
}
