/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.util.ManipuladorCSV;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.omnifaces.util.Faces;

/**
 *
 * @author ian
 */
@ViewScoped
@Named
public class ProfessorBean implements Serializable{
    @Inject
    UsuarioDao dao;
    List<Usuario> professores;
    Part part;
    
    @PostConstruct
    public void init(){
        professores = new ArrayList<>();
        this.professores = this.dao.usuariosComNivelDeAcesso(NivelAcesso.PROFESSOR);
//        if(this.professores.size()==0){
//            this.professores = new ArrayList<>();
//        }
    }
    
    public void salvar(){
        if(part==null){
            return;
        }
        List<Usuario> professoresAux = new ArrayList<>();
        try {
            professoresAux = ManipuladorCSV.toListProfessor(part);
            if(professoresAux.size()>0){
                dao.salvarProfessores(new ArrayList<>(professoresAux));
                this.professores = professoresAux;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professores cadastrados!", null));

            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento com extenção inválida ou vazio!", null));
            }
            
            
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na leitura deste arquivo!", null));
        } catch (ArrayIndexOutOfBoundsException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coluna(s) a mais na estrutura do arquivo!", null));
        }
    } 
    
    public String download(){
        try {
            File file = ManipuladorCSV.toProfessorCsv(dao.usuariosComNivelDeAcesso(NivelAcesso.PROFESSOR));
            Faces.sendFile(file, true);
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void remover(Usuario usuario){
        this.dao.removerProfessor(usuario);
        this.professores = this.dao.usuariosComNivelDeAcesso(NivelAcesso.PROFESSOR);
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    
    public List<Usuario> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Usuario> professores) {
        this.professores = professores;
    }
    
    
}
