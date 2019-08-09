package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.controle.util.ManipuladorCSV;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class TestBean {

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private AlunoDao alunoDao;

    @PostConstruct
    private void init() {
//        Usuario u1 = new Usuario("1", "1", "1", "Usuario1", NivelAcesso.PROFESSOR, Boolean.TRUE);
//        Usuario u2 = new Usuario("2", "2", "2", "Usuario2", NivelAcesso.ADMINISTRADOR, Boolean.TRUE);
//        Usuario u3 = new Usuario("3", "3", "3", "Usuario3", NivelAcesso.CAEST, Boolean.TRUE);
//        Usuario u4 = new Usuario("4", "4", "4", "Usuario4", NivelAcesso.GESTOR, Boolean.TRUE);
//        try {
//            usuarioDao.salvar(u1);
//            usuarioDao.salvar(u2);
//            usuarioDao.salvar(u3);
//            usuarioDao.salvar(u4);
//        } catch (MatriculaExistenteException ex) {
//            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            //Ordem para colunas no CSV matricula, senha, email, nome, nivelAcesso;
////            List<Usuario> listUsuarios = ManipuladorCSV.toListUsuario("/home/ian/Projetos_Programas/Java/Controle-de-Refeicoes/usuario.csv");
////            for (Usuario u : listUsuarios) {
////                usuarioDao.salvar(u);
////            }
//
//            //matricula, nome, edital, tipobeneficio
//            List<Aluno> listAlunos = ManipuladorCSV.toListAlunos("/home/ian/Projetos_Programas/Java/Controle-de-Refeicoes/aluno.csv");
//            for (Aluno a : listAlunos) {
//                alunoDao.salvar(a);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (MatriculaExistenteException ex) {
//            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
