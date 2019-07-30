package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.implementacoes.UsuarioDaoImpl;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.DaoIF;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class GestorBean {

    private Usuario usuario;
    private DaoIF gestorDao;


    public String cadastrar(){
        try {
            usuario.setAtivo(true);
            usuario.setNivelAcesso(NivelAcesso.GESTOR);
            gestorDao.salvar(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gestor cadastrado com sucesso!", null));

        } catch (MatriculaExistenteException e) {
            e.printStackTrace();
        }
        return "";
    }

    

}
