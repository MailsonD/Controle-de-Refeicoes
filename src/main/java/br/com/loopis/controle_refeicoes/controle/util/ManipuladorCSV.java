package br.com.loopis.controle_refeicoes.controle.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.loopis.controle_refeicoes.modelo.entidades.Aluno;
import br.com.loopis.controle_refeicoes.modelo.entidades.Beneficio;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import javax.servlet.http.Part;

public class ManipuladorCSV {

    public static List<Usuario> toListProfessor(String path) throws IOException {
        String separador = ",";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String linha = null;
        reader.readLine();
        List<Usuario> lista = new ArrayList<>();
        while ((linha = reader.readLine()) != null) {
            String[] dadosObjeto = linha.split(separador);
            lista.add(
                    new Usuario(
                            dadosObjeto[0],
                            dadosObjeto[1],
                            dadosObjeto[2],
                            dadosObjeto[3],
                            NivelAcesso.PROFESSOR)
            );
        }
        reader.close();
        return lista;
    }

    public static List<Usuario> toListProfessor(Part part) throws IOException, ArrayIndexOutOfBoundsException {
        if (!(part.getContentType().equals("text/csv"))) {
            return new ArrayList<>();
        }
        String separador = ",";
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));
        String linha = null;
        reader.readLine();
        List<Usuario> lista = new ArrayList<>();
        while ((linha = reader.readLine()) != null) {
            String[] dadosObjeto = linha.split(separador);
            lista.add(
                    new Usuario(
                            dadosObjeto[0],
                            dadosObjeto[1],
                            dadosObjeto[2],
                            dadosObjeto[3],
                            NivelAcesso.PROFESSOR)
            );
        }
        reader.close();
        return lista;
    }

    public static List<Aluno> toListAlunos(String path) throws IOException {
        String separador = ",";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String linha = null;
        reader.readLine();
        List<Aluno> listaAlunos = new ArrayList<>();
        while ((linha = reader.readLine()) != null) {
            String[] dadosObjeto = linha.split(separador);
            Aluno a = new Aluno(
                    dadosObjeto[0],
                    dadosObjeto[1]);
            Beneficio b = new Beneficio(
                    TipoBeneficio.valueOf(dadosObjeto[2]),
                    dadosObjeto[3]);
            b.setAlunoBeneficiado(a);
            a.setBeneficio(b);
            listaAlunos.add(a);

        }
        reader.close();
        return listaAlunos;
    }
    
    public static List<Aluno> toListAlunos(Part part) throws IOException, ArrayIndexOutOfBoundsException {
        if (!(part.getContentType().equals("text/csv"))) {
            return new ArrayList<>();
        }
        String separador = ",";
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));
        String linha = null;
        reader.readLine();
        List<Aluno> listaAlunos = new ArrayList<>();
        while ((linha = reader.readLine()) != null) {
            String[] dadosObjeto = linha.split(separador);
            Aluno a = new Aluno(
                    dadosObjeto[0],
                    dadosObjeto[1]);
            Beneficio b = new Beneficio(
                    TipoBeneficio.valueOf(dadosObjeto[2]),
                    dadosObjeto[3]);
            b.setAlunoBeneficiado(a);
            a.setBeneficio(b);
            listaAlunos.add(a);
        }
        reader.close();
        return listaAlunos;
    }
    
    
}
