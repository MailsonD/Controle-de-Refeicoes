package br.com.loopis.controle_refeicoes.rest.dto;

import br.com.loopis.controle_refeicoes.modelo.entidades.Usuario;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "JustificativaCEST")
public class JustificativaCAESTDTO implements Serializable {


    private String justificativa;

    private Usuario usuarioCAEST;

    public JustificativaCAESTDTO() {
    }

    public JustificativaCAESTDTO(String justificativa, Usuario usuarioCAEST) {
        this.justificativa = justificativa;
        this.usuarioCAEST = usuarioCAEST;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JustificativaCAESTDTO that = (JustificativaCAESTDTO) o;
        return Objects.equals(justificativa, that.justificativa) &&
                Objects.equals(usuarioCAEST, that.usuarioCAEST);
    }

    @Override
    public int hashCode() {
        return Objects.hash(justificativa, usuarioCAEST);
    }

    @Override
    public String toString() {
        return "JustificativaCAESTDTO{" +
                "justificativa='" + justificativa + '\'' +
                ", usuarioCAEST=" + usuarioCAEST +
                '}';
    }
}
