package br.com.loopis.controle_refeicoes.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class UserFirebaseDTO {

    private String matricula;
    private String token;

    public UserFirebaseDTO() {
    }

    public UserFirebaseDTO(String matricula, String token) {
        this.matricula = matricula;
        this.token = token;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFirebaseDTO that = (UserFirebaseDTO) o;
        return Objects.equals(matricula, that.matricula) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, token);
    }

    @Override
    public String toString() {
        return "UserFirebaseDTO{" +
                "matricula='" + matricula + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
