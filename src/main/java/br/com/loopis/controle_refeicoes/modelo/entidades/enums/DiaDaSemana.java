/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loopis.controle_refeicoes.modelo.entidades.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ian
 */
public enum DiaDaSemana {
    DOMINGO(0),
    SEGUNDA(1),
    TERÇA(2),
    QUARTA(3),
    QUINTA(4),
    SEXTA(5),
    SÁBADO(6);

    
    private int value;
    private static Map map = new HashMap<>();

    private DiaDaSemana(int value) {
        this.value = value;
    }

    static {
        for (DiaDaSemana dia : DiaDaSemana.values()) {
            map.put(dia.value, dia);
        }
    }

    public static DiaDaSemana valueOf(int pageType) {
        return (DiaDaSemana) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
