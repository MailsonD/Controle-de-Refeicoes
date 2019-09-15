package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;

public interface AlunoDao extends DaoIF<AlunoBeneficiado> {
    public AlunoBeneficiado buscarPorMatricula(String matricula);

    public void salvar(AlunoBeneficiado aluno);

    public void removerTodos();
}
