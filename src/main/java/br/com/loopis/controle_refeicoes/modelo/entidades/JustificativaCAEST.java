package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class JustificativaCAEST implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Pedido pedido;
    @Lob
    private String justificativa;
    @ManyToOne
    private Usuario usuarioCAEST;


}
