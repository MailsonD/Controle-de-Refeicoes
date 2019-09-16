package br.com.loopis.controle_refeicoes.modelo.entidades.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum StatusPedido {
    PENDENTE,
    ACEITO,
    RECUSADO
}
