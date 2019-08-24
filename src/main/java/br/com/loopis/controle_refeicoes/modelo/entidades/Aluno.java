package br.com.loopis.controle_refeicoes.modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Leanderson Coelho
 * **/
@Entity
public class Aluno implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String matricula;
    private String nome;
    @OneToOne(mappedBy = "alunoBeneficiado", cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private Beneficio beneficio;

    public Aluno() {
    }

    public Aluno(int id, String matricula, String nome) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
    }

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

	@Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", matricula=" + matricula + ", nome=" + nome + '}';
    }
    
    
}
