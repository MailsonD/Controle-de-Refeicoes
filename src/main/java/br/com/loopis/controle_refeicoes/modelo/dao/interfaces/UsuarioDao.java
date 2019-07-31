package br.com.loopis.controle_refeicoes.modelo.dao.interfaces;

import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;

import java.util.List;

public interface UsuarioDao extends DaoIF<Usuario> {

    public Usuario buscarPorMatricula(Usuario usuario) throws UsuarioNaoEncontradoException;
    public Usuario autenticar(Usuario usuario) throws SenhaInvalidaException, UsuarioNaoEncontradoException;
    public List<Usuario> usuariosComNivelDeAcesso(NivelAcesso nivelAcesso);
}
