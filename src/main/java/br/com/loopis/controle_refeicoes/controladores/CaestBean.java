/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.controladores;

/**
 *
 * @author ian
 */
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
public class CaestBean implements Serializable{

    private Usuario caest;
    @Inject
    private UsuarioDao dao;
    private List<Usuario> usuariosCaest = new ArrayList<>();

    @PostConstruct
    public void init() {
        caest = new Usuario();
        usuariosCaest = dao.usuariosComNivelDeAcesso(NivelAcesso.CAEST);
    }


    public void cadastrar(){
        try {
            caest.setAtivo(true);
            caest.setNivelAcesso(NivelAcesso.CAEST);
            dao.salvar(caest);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário CAEST cadastrado com sucesso!", null));

            usuariosCaest = dao.usuariosComNivelDeAcesso(NivelAcesso.CAEST);
        } catch (MatriculaExistenteException e) {
            System.out.println("Foi");
        }
    }
    
    public void remover(Usuario usuario){
        this.dao.remover(usuario);
        this.usuariosCaest = this.dao.usuariosComNivelDeAcesso(NivelAcesso.CAEST);
    }
    
    public Usuario getCaest() {
        return caest;
    }

    public void setCaest(Usuario caest) {
        this.caest = caest;
    }

    public List<Usuario> getUsuariosCaest() {
        return usuariosCaest;
    }

    public void setUsuariosCaest(List<Usuario> usuariosCaest) {
        this.usuariosCaest = usuariosCaest;
    }
    
}
