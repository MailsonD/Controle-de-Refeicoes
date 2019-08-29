package br.com.loopis.controle_refeicoes.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.com.loopis.controle_refeicoes.modelo.entidades.AlunoBeneficiado;
import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.NivelAcesso;
import br.com.loopis.controle_refeicoes.modelo.entidades.enums.TipoBeneficio;
import javax.servlet.http.Part;

public class ManipuladorCSV {

    private final static String separador = ",";

    public static List<Usuario> toListProfessor(Part part) throws IOException, ArrayIndexOutOfBoundsException {
        if (!(part.getContentType().equals("text/csv"))) {
            return new ArrayList<>();
        }
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
                            NivelAcesso.PROFESSOR)
            );
        }
        reader.close();
        return lista;
    }


    public static List<AlunoBeneficiado> toListAlunos(Part part) throws IOException {
        if (!(part.getContentType().equals("text/csv"))) {
            return new ArrayList<>();
        }
        String separador = ",";
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));
        String linha = null;
        reader.readLine();
        List<AlunoBeneficiado> listaAlunos = new ArrayList<>();
        while ((linha = reader.readLine()) != null) {
            String[] dadosObjeto = linha.split(separador);
            
            AlunoBeneficiado ab = new AlunoBeneficiado(
                    dadosObjeto[0],
                    dadosObjeto[1],
                    TipoBeneficio.valueOf(dadosObjeto[2]),
                    dadosObjeto[3]);//edital
            
            listaAlunos.add(ab);
        }
        reader.close();
        return listaAlunos;
    }

    public static File toProfessorCsv(List<Usuario> professores) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("_professor.csv"), "UTF-8"));
        StringBuffer linha = new StringBuffer();
        linha.append("Matrícula,Email,Nome,Nível de Acesso");
        bw.write(linha.toString());
        bw.newLine();
        for (Usuario professor : professores) {
            linha = new StringBuffer();
            linha.append(professor.getMatricula());
            linha.append(separador);
            linha.append(professor.getEmail());
            linha.append(separador);
            linha.append(professor.getNome());
            linha.append(separador);
            linha.append(professor.getNivelAcesso().name());
            linha.append(separador);
            bw.write(linha.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
        return new File("_professor.csv");

    }
    public static File toAlunoCsv(List<AlunoBeneficiado> alunos) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("_aluno.csv"), "UTF-8"));
        StringBuffer linha = new StringBuffer();
        linha.append("Matrícula,Nome,Tipo de Benefício,Edital");
        bw.write(linha.toString());
        bw.newLine();
        for (AlunoBeneficiado aluno : alunos) {
            linha = new StringBuffer();
            linha.append(aluno.getMatricula());
            linha.append(separador);
            linha.append(aluno.getNome());
            linha.append(separador);
            linha.append(aluno.getTipoBeneficio());
            linha.append(separador);
            linha.append(aluno.getEditalBeneficio());
            linha.append(separador);
            bw.write(linha.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
        return new File("_aluno.csv");
    }

}
