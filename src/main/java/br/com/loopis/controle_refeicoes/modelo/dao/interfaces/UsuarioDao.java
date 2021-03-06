package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;

import java.util.List;

public interface UsuarioDao extends DaoIF<Usuario> {
    void salvar(Usuario object) throws MatriculaExistenteException;
    public Usuario buscarPorMatricula(String matricula) throws UsuarioNaoEncontradoException;
    public Usuario autenticar(Usuario usuario) throws SenhaInvalidaException, UsuarioNaoEncontradoException;
    public List<Usuario> usuariosComNivelDeAcesso(NivelAcesso nivelAcesso);

    public void salvarProfessores(List<Usuario> professores);

    public void removerProfessor(Usuario u);
}
