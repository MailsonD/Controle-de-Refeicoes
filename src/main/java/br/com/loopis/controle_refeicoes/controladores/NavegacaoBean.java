package br.com.loopis.controle_refeicoes.controladores;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 *
 */

@Named
@RequestScoped
public class NavegacaoBean {

    public String mudarPagina(String alvo){
        return alvo;
    }

//    public String mudarPaginaVisitante(String alvo){
//        return alvo;
//    }

}
