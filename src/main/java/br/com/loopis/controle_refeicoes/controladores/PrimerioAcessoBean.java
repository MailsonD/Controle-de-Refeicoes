package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.util.GeradorDeSenha;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class PrimerioAcessoBean {

    @Inject
    private UsuarioDao usuarioDao;

    private String email;
    private String matricula;

    public String abrirPagina(){
        System.out.println("teste");
        return "meuPrimeiroAcesso";
    }

    public String submit(){
        try{
            Usuario user = usuarioDao.buscarPorMatricula(matricula);
            if(user != null){
                if(!user.getSenha().isEmpty()){
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "A conta informada já possui uma senha!", null));
                }else if(user.getEmail().equals(email)){
                    String senhaGerada = GeradorDeSenha.gerarSenhaAleatoria();
                    user.setSenha(senhaGerada);
                    usuarioDao.atualizar(user);
                    //TODO enviar email. Gerar senha.
                    return "acessoGerado";
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este não é seu email de acesso!", null));
                }
            }else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula ou email inválidos!", null));
            }
            return "";
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
