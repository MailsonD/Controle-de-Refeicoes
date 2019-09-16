package br.com.loopis.controle_refeicoes.util;

import br.com.loopis.controle_refeicoes.modelo.excessoes.ReadTokenException;
import br.com.loopis.controle_refeicoes.modelo.excessoes.StoreTokenException;
import org.eclipse.persistence.oxm.record.OutputStreamRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenUtil {

    public static void armazenarToken(String key, String token) throws StoreTokenException {
        try(FileOutputStream out = new FileOutputStream(key + ".txt")) {
            out.write(token.getBytes());
            salvarKey(key);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StoreTokenException();
        }
    }

    public static String buscarToken(String key) throws ReadTokenException {
        try(FileInputStream in = new FileInputStream(key + ".txt")){
            byte[] token = new byte[1024];
            in.read(token);
            return new String(token).trim();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadTokenException();
        }
    }

    public static boolean removerToken(String key){
        removerKey(key);
        File file = new File(key + ".txt");
        return file.delete();
   }

    public static List<String> buscarKeys(){
        List<String> keys = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader (
                        new FileInputStream("keys.txt")
                )
        )){
            String linha = "";
            while ((linha = br.readLine()) != null){
                keys.add(linha);
            }
            return keys;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   private static void salvarKey(String key){
        try(BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("keys.txt")
                )
        )){
            bw.write(key);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

   private static void removerKey(String key){
        List<String> keys = buscarKeys().stream().filter(k -> !k.equals(key)).collect(Collectors.toList());
        File file = new File("keys.txt");
        file.delete();
        keys.forEach(k -> {
            salvarKey(k);
        });
   }





}
