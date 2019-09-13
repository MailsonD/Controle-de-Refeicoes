package br.com.loopis.controle_refeicoes.rest.dto;

import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import java.util.List;

public class AlunosDTO {
    
    public List<Aluno> alunos;

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
}
