package fr.cnp.jug.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class Bonbon extends AbstractPersistable<Long> {

    private String nom;

    private String description;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
