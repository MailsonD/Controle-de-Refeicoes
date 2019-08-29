package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ServiceAluno {
    
    @Inject
    private AlunoDao alunoDao;
    
    public void salvar(Aluno a) throws MatriculaExistenteException {
        alunoDao.salvar(a);
    }

    public void atualizar(Aluno a) {
        alunoDao.atualizar(a);
    }

    public void remover(Aluno a) {
        alunoDao.remover(a);
    }

    public Aluno buscar(Object key) {
        return alunoDao.buscar(key);
    }

    public List<Aluno> listar() {
        return alunoDao.listar();
    }
    
}
