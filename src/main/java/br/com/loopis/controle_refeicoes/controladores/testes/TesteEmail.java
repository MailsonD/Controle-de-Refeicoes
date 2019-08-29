package br.com.loopis.controle_refeicoes.controladores.testes;

import br.com.loopis.controle_refeicoes.util.MailUtil;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Logger;

@Singleton
@Startup
public class TesteEmail {

    private Logger log = Logger.getLogger(TesteEmail.class.getName());

    @PostConstruct
    private void init(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Enviando o primerio email");
        MailUtil.enviarEmail();
    }
}
