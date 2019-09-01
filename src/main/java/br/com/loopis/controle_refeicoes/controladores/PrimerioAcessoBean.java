package br.com.loopis.controle_refeicoes.controladores;

import br.com.loopis.controle_refeicoes.modelo.dao.interfaces.UsuarioDao;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.excessoes.UsuarioNaoEncontradoException;
import br.com.loopis.controle_refeicoes.util.GeradorDeSenha;
import br.com.loopis.controle_refeicoes.util.MailUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@RequestScoped
@Named
public class PrimerioAcessoBean {

    @Inject
    private UsuarioDao usuarioDao;

    private String email;
    private String matricula;

    private Logger log = Logger.getLogger(PrimerioAcessoBean.class.getName());

    public String abrirPagina(){
        System.out.println("teste");
        return "meuPrimeiroAcesso";
    }

    public String submit(){
        try{
            Usuario user = usuarioDao.buscarPorMatricula(matricula);
            if(user != null){
                if(user.getSenha() != null && !user.getSenha().isEmpty()){
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "A conta informada já possui uma senha!", null));
                }else if(user.getEmail().equals(email)){
                    log.info("gerando a senha");
                    String senhaGerada = GeradorDeSenha.gerarSenhaAleatoria();
                    user.setSenha(senhaGerada);
                    usuarioDao.atualizar(user);
                    log.info("senha gerada");
                    log.info("Enviando email");
                    MailUtil.enviarEmail(email,user.getNome(),senhaGerada);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique seu email!", null));

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
        }catch (UsuarioNaoEncontradoException e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inexistente!", null));
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
