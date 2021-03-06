package br.com.loopis.controle_refeicoes.modelo.entidades.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Turma {
    DESENHO_CI("DESENHO DE CONSTRUÇÃO CIVIL - PROEJA"),
    INTED1("INTEGRADO EM EDIFICAÇÕES 1"),
    INTED2("INTEGRADO EM EDIFICAÇÕES 2"),
    INTED3("INTEGRADO EM EDIFICAÇÕES 3"),
    INTED4("INTEGRADO EM EDIFICAÇÕES 4"),
    INTEL1("INTEGRADO EM ELETROMECÂNICA 1"),
    INTEL2("INTEGRADO EM ELETROMECÂNICA 2"),
    INTEL3("INTEGRADO EM ELETROMECÂNICA 3"),
    INTEL4("INTEGRADO EM ELETROMECÂNICA 4"),
    INTIN1("INTEGRADO EM INFORMÁTICA 1"),
    INTIN2("INTEGRADO EM INFORMÁTICA 2"),
    INTIN3("INTEGRADO EM INFORMÁTICA 3"),
    INTIN4("INTEGRADO EM INFORMÁTICA 4"),
    MEIO_AMBIE("MEIO AMBIENTE - PROEJA"),
    TED("SUBSEQUENTE EM EDIFICAÇÕES"),
    TEL("SUBSEQUENTE EM ELETROMECÂNICA"),
    ADS("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS"),
    AUTIND("AUTOMAÇÃO INDUSTRIAL"),
    CONTROLE("CONTROLE E AUTOMAÇÃO"),
    CIVIL("ENGENHARIA CIVIL"),
    LM("LICENCIATURA EM MATEMÁTICA");


    private String descricao;

    Turma(String asdad) {
        this.descricao = asdad;
    }

    public String getDescricao(){
        return this.descricao;
    }

}
