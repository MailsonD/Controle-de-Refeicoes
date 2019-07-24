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

    public JustificativaCAEST() {
    }

    public JustificativaCAEST(int id, Pedido pedido, String justificativa, Usuario usuarioCAEST) {
        this.id = id;
        this.pedido = pedido;
        this.justificativa = justificativa;
        this.usuarioCAEST = usuarioCAEST;
    }

    public JustificativaCAEST(Pedido pedido, String justificativa, Usuario usuarioCAEST) {
        this.pedido = pedido;
        this.justificativa = justificativa;
        this.usuarioCAEST = usuarioCAEST;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Usuario getUsuarioCAEST() {
        return usuarioCAEST;
    }

    public void setUsuarioCAEST(Usuario usuarioCAEST) {
        this.usuarioCAEST = usuarioCAEST;
    }

    @Override
    public String toString() {
        return "JustificativaCAEST{" + "id=" + id + ", pedido=" + pedido + ", justificativa=" + justificativa + ", usuarioCAEST=" + usuarioCAEST + '}';
    }


}
