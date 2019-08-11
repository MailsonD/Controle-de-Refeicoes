package br.com.loopis.controle_refeicoes.controladores;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.excessoes.SenhaInvalidaException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;

@SessionScoped
@Named
public class AutenticacaoBean implements Serializable{
	@Inject
	private UsuarioDao usuarioDao;
	
	private Usuario usuario = new Usuario();
	
	public String login() {
		try {
			usuario = usuarioDao.autenticar(usuario);
		} catch (SenhaInvalidaException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!", null));
			return "nao-autorizado";
		} catch (UsuarioNaoEncontradoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este cadastro não foi encontrado!", null));
			return "nao-autorizado";
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("usuarioLogado", this.usuario);
		return "autorizado";
	}
	
	public String logout() {
		this.usuario=null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "desautenticar";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
