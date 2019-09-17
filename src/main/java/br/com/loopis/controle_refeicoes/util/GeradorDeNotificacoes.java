package br.com.loopis.controle_refeicoes.util;

import br.com.loopis.controle_refeicoes.modelo.excessoes.ReadTokenException;
import com.squareup.okhttp.*;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.parser.Token;
import sun.net.www.http.HttpClient;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import java.io.IOException;
import java.net.*;
import org.json.JSONObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class GeradorDeNotificacoes {

    private OkHttpClient client;

    private static final String URL = "https://fcm.googleapis.com/fcm/send";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final Logger log = Logger.getLogger(GeradorDeNotificacoes.class.getName());
    private static final String API_KEY = "AAAAm1YjSZQ:APA91bFgXauKfTEW9Q6NEi23B40Nq99tSu5F2yJJImdjksSWbWzoQjwmMIehA3qK6H0rGsqZr9WdtRxBIMZiVt4T6Cno12Ik16jPCblfPyLcq1AaxcCcNK8AaN9irCRMdTYZ5T6uzVrN";

    @PostConstruct
    private void init(){
        client = new OkHttpClient();
    }

    public void enviar(String msg){
        List<String> keys = TokenUtil.buscarKeys();
            keys.forEach(k -> {
                try{

                    String token = TokenUtil.buscarToken(k);

                    System.out.println("Enviando" + msg + " para: " + k);

                    Request.Builder builder = new Request.Builder();

                    builder.url(URL);
                    RequestBody body = RequestBody.create(MEDIA_TYPE, gerarJSON(msg, token));
                    builder.post(body);
                    builder.addHeader("Authorization", "key=" + API_KEY);


                    Request request = builder.build();

                    Response response = client.newCall(request).execute();
                    log.log(Level.INFO, response.body().string());
//                    URL url = new URL("http://");
//                    HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty("Content-type", "application/json");
//                    conn.getOutputStream().write(("{" +
//                            ""
//                            + "}").getBytes());
//                    String content = (String) conn.getContent();
//                    conn.disconnect();
                } catch (IOException | ReadTokenException e) {
                    e.printStackTrace();
                }
            });

    }

    private String gerarJSON(String msg, String token) {
        JSONObject json = new JSONObject();
        JSONObject notification = new JSONObject();
        notification.put("title", "Controle de Refeições - IFPB");
        notification.put("body", msg);

        json.put("notification", notification);
        json.put("to", token);
        return json.toString();
    }

}
