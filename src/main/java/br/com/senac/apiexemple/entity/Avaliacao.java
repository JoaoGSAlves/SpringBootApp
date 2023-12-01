package br.com.senac.apiexemple.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Avaliacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5088607790334764863L;

	@EmbeddedId
	private AlunoDisciplina alunoDisciplina;
	
	private String conceito;

	public AlunoDisciplina getAlunoDisciplina() {
		return alunoDisciplina;
	}

	public void setAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		this.alunoDisciplina = alunoDisciplina;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
}
