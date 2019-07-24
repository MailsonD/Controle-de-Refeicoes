package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.Id;

public class JustificativaCAEST {

    @Id
    private int id;
    private Pedido pedido;
    private String justificativa;
    private Usuario usuarioCAEST;


}
