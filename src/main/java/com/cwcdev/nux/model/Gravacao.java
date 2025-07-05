package com.cwcdev.nux.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Gravacao {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numeroSlot; // número do pedal
    private String tema;    // exemplo: Base em C

    @Lob
    private String assunto; // texto longo: exemplo: explicação, modos, etc.
    
    public Gravacao() {}

	public Gravacao(Long id, int numeroSlot, String tema, String assunto) {
		this.id = id;
		this.numeroSlot = numeroSlot;
		this.tema = tema;
		this.assunto = assunto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroSlot() {
		return numeroSlot;
	}

	public void setNumeroSlot(int numeroSlot) {
		this.numeroSlot = numeroSlot;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Gravacao other = (Gravacao) obj;
		return Objects.equals(id, other.id);
	}
}
