package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.controladores.ProfessorBean;
import br.com.loopis.controle_refeicoes.controle.util.ManipuladorCSV;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
//        List<Usuario> l = new ArrayList<>();
//        l.add(new Usuario("123", "321", "email@gmail.com", "Zé", NivelAcesso.ADMINISTRADOR));
//        try {
//            File f = ManipuladorCSV.toProfessorCsv(l);
//            System.out.println("\n"+f.getAbsolutePath());
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
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
