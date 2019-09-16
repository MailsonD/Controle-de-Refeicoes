package br.com.loopis.controle_refeicoes.util;

import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import java.io.IOException;
import java.net.*;
import java.util.List;


public class GeradorDeNotificacoes {

    public static void enviar(String msg){
        List<String> keys = TokenUtil.buscarKeys();
            keys.forEach(k -> {
                try{
                    System.out.println("Enviando" + msg + " para: " + k);
                    URL url = new URL("http://");
                    HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.getOutputStream().write(("{" +
                            ""
                            + "}").getBytes());
                    String content = (String) conn.getContent();
                    conn.disconnect();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }

}
