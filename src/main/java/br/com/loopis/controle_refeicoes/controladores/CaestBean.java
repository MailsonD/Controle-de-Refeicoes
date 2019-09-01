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
import br.com.loopis.controle_refeicoes.util.ManipuladorCSV;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.AlunoDao;
import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.excessoes.MatriculaExistenteException;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;


@ViewScoped
@Named
public class CaestBean implements Serializable{

    private Usuario caest;
    @Inject
    private UsuarioDao dao;
    @Inject
    private AlunoDao alunoDao;
    private List<Usuario> usuariosCaest = new ArrayList<>();
    private List<AlunoBeneficiado> alunos = new ArrayList<>();
    private Part part;

    @PostConstruct
    public void init() {
        caest = new Usuario();
        usuariosCaest = dao.usuariosComNivelDeAcesso(NivelAcesso.CAEST);
        alunos = alunoDao.listar();
    }

    public void salvarAlunosCsv(){
        List<AlunoBeneficiado> alunosAux = new ArrayList<>();
        try {
            alunosAux = ManipuladorCSV.toListAlunos(part);
            if(alunosAux.size()>0){
                for(AlunoBeneficiado aluno: alunosAux){
                    this.alunoDao.salvar(aluno);
                }
                this.alunos = alunosAux;
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento com extenção inválida ou vazio!", null));
            }


        } catch (MatriculaExistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alunos com matrículas repetidas!", null));
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na leitura deste arquivo!", null));
        } catch (ArrayIndexOutOfBoundsException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coluna(s) a mais na estrutura do arquivo!", null));
        }
    }

    public void cadastrar(){
        try {
            caest.setAtivo(true);
            caest.setNivelAcesso(NivelAcesso.CAEST);
            dao.salvar(caest);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário CAEST cadastrado com sucesso!", null));
            caest = new Usuario();
            usuariosCaest = dao.usuariosComNivelDeAcesso(NivelAcesso.CAEST);
        } catch (MatriculaExistenteException e) {
            System.out.println("Foi");
        }
    }
    
    public void remover(Usuario usuario){
        this.dao.remover(usuario);
        this.usuariosCaest = this.dao.usuariosComNivelDeAcesso(NivelAcesso.CAEST);
    }

    public void removerAluno(AlunoBeneficiado aluno){
        this.alunoDao.remover(aluno);
        this.alunos = alunoDao.listar();
    }

    public List<AlunoBeneficiado> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoBeneficiado> alunos) {
        this.alunos = alunos;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
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
