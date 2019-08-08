package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;


@ViewScoped
@Named
public class GestorBean implements Serializable{

    private Usuario usuario;
    @Inject
    private UsuarioDao gestorDao;
    private List<Usuario> gestores = new ArrayList<>();

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        gestores = gestorDao.usuariosComNivelDeAcesso(NivelAcesso.GESTOR);
    }


    public String cadastrar(){
        try {
            usuario.setAtivo(true);
            usuario.setNivelAcesso(NivelAcesso.GESTOR);
            gestorDao.salvar(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestor cadastrado com sucesso!", null));

            gestores = gestorDao.usuariosComNivelDeAcesso(NivelAcesso.GESTOR);
        } catch (MatriculaExistenteException e) {
            System.out.println("Foi");
        }
        return "";
    }
    
    public void remover(Usuario usuario){
        this.gestorDao.remover(usuario);
        this.gestores = this.gestorDao.usuariosComNivelDeAcesso(NivelAcesso.GESTOR);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getGestores() {
        return gestores;
    }

    public void setGestores(List<Usuario> gestores) {
        this.gestores = gestores;
    }
}
