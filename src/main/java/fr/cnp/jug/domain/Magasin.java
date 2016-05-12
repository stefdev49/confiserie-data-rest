package fr.cnp.jug.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class Magasin extends AbstractPersistable<Long> {

	private String nom;

	private String description;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Adresse adresse;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Bonbon> bonbons;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Bonbon> getBonbons() {
		return bonbons;
	}

	public void setBonbons(List<Bonbon> bonbons) {
		this.bonbons = bonbons;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
