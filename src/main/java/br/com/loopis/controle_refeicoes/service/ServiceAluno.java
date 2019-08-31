package br.com.loopis.controle_refeicoes.service;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ServiceAluno {
    
    @Inject
    private AlunoDao alunoDao;
    
    public void salvar(AlunoBeneficiado a) throws MatriculaExistenteException {
        alunoDao.salvar(a);
    }

    public void atualizar(AlunoBeneficiado a) {
        alunoDao.atualizar(a);
    }

    public void remover(AlunoBeneficiado a) {
        alunoDao.remover(a);
    }

    public AlunoBeneficiado buscar(Object key) {
        return alunoDao.buscar(key);
    }

    public List<AlunoBeneficiado> listar() {
        return alunoDao.listar();
    }
    
}
