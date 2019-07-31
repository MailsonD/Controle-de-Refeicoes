package br.com.loopis.controle_refeicoes.controle.testes;

import br.com.loopis.controle_refeicoes.controle.util.ManipuladorCSV;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.*;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Singleton
@Startup
public class TestBean {

    @Inject
    private UsuarioDao usuarioDao;
    
    @Inject
    private AlunoDao alunoDao;

    @PostConstruct
    private void init(){
    	try {
    		//Ordem para colunas no CSV matricula, senha, email, nome, nivelAcesso;
			List<Usuario> listUsuarios= ManipuladorCSV.toListUsuario("/home/ian/Projetos_Programas/Java/Controle-de-Refeicoes/usuario.csv");
			for(Usuario u: listUsuarios) {
				usuarioDao.salvar(u);
			}
			
			
			//matricula, nome, edital, tipobeneficio
			List<Aluno> listAlunos= ManipuladorCSV.toListAlunos("/home/ian/Projetos_Programas/Java/Controle-de-Refeicoes/aluno.csv");
			for(Aluno a: listAlunos) {
				alunoDao.salvar(a);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
