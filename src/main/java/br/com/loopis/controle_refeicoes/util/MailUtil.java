package br.com.loopis.controle_refeicoes.util;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

public class MailUtil {

    private static final Properties props = new Properties();
    private static final String MAIL_HOST = "smtp.gmail.com";
    private static final String MAIL_PORT = "587";
    private static final String MAIL_SOCKET_PORT = "465";
    private static final String MAIL_SOCKER_CLASS = "javax.net.ssl.SSLSocketFactory";
    private static final String MAIL_CHARSET = "UTF-8";
    private static final boolean MAIL_AUTH = true;
    private static final boolean MAIL_SOCKET_FALLBACK = false;


    private static final String MAIL_ADRESS = "testerson.testador@gmail.com";
    private static final String MAIL_NAME = "Controle de Refeições - IFPB";
    private static final String MAIL_PASSWORD = "testebanco2";

    private static final String ACCEPT_HTML = "text/html;; x-java-content-handler=com.sun.mail.handlers.text_html";
    private static final String ACCEPT_TEXT_PLAIN = "text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain";

    private static final MailcapCommandMap mc;
    private static final Session session;

    private static Logger log = Logger.getLogger(MailUtil.class.getName());

    static {
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth", MAIL_AUTH);
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.socketFactory.port", MAIL_SOCKET_PORT);
        props.put("mail.smtp.socketFactory.class",MAIL_SOCKER_CLASS);
        props.put("mail.smtp.socketFactory.fallback", MAIL_SOCKET_FALLBACK);
        props.put("mail.mine.charset",MAIL_CHARSET);

        session = Session.getInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                MAIL_ADRESS, MAIL_PASSWORD
                        );
                    }
                });

        session.setDebug(true);

        mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap(ACCEPT_HTML);
        mc.addMailcap(ACCEPT_TEXT_PLAIN);
        CommandMap.setDefaultCommandMap(mc);
    }

    public static void enviarEmail(String emailDestinatario, String nomeDestinatario, String senhaGerada){
        log.info("Iniciando Envio de email");
        try{
            String htmlBody = "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                    "    <tr>\n" +
                    "        <td align=\"center\" bgcolor=\"#32CD32\" style=\"padding: 40px 0 30px 0;\">\n" +
                    "            <img src=\"https://media.giphy.com/media/XreQmk7ETCak0/giphy.gif\" alt=\"Criando Mágica de E-mail\" width=\"300\" height=\"230\" style=\"display: block;\" />\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"border: 1px solid #cccccc; padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif;\">\n" +
                    "\n" +
                    "            <center>\n" +
                    "                <b style=\"font-size: 24px\">Caro, "+nomeDestinatario+".</b>\n" +
                    "                <br>\n" +
                    "                <h4 style=\"font-size: 24px\"> Seja bem vindo ao nosso Sistema!!</h4>\n" +
                    "                <h4 style=\"font-size: 20px\">Sua senha foi gerada automaticamente.</h4>\n" +
                    "                <br>\n" +
                    "                <b style=\"font-size: 20px\"> senha ,</b>\n" +
                    "                <b style=\"font-size: 24px\">"+senhaGerada+"</b>\n" +
                    "            </center>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "</table>";

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(
                    MAIL_ADRESS, MAIL_NAME)
            );

            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailDestinatario, nomeDestinatario)
            );

            msg.setSubject("Confirmação de acesso a - Controle de Refeições IFPB");

            msg.setContent(htmlBody, "text/html");

            Transport.send(msg);

            log.info("Email enviado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
