package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.implementacoes.AlunoDaoImpl;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */

@Named
@ViewScoped
public class PublicoBean implements Serializable {

    private List<AlunoBeneficiado> alunos;
    private AlunoDao alunoDao;
    private LocalDate data;

    @PostConstruct
    public void init(){
        alunos = new ArrayList<>();
        alunoDao = new AlunoDaoImpl();
        data = LocalDate.now();
    }


    public void listar(){

    }

    public List<AlunoBeneficiado> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoBeneficiado> alunos) {
        this.alunos = alunos;
    }

    public AlunoDao getAlunoDao() {
        return alunoDao;
    }

    public void setAlunoDao(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
