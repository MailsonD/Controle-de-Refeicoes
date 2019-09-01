/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.controladores;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author caique
 */
@Named
@RequestScoped
public class TemplateRender {

    public String getStyleClass() {

        String requestContextPath = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestPathInfo();
        
        if("/WEB-INF/Template.xhtml".equals(requestContextPath)){
            System.out.println(requestContextPath);
            
            return "deu certro";
        }else{
            return "AAAA";
        }
        
           

      
        
    }
}
