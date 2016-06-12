package fr.stef.jug.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Magasin de bonbons.
 *
 * @author stef
 *
 */
@SuppressWarnings("serial")
@Entity
public class Magasin extends AbstractPersistable<Long> {

  private String nom;

  private String description;

  @JsonInclude(Include.NON_EMPTY)
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Adresse adresse;

  @JsonInclude(Include.NON_EMPTY)
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (adresse == null ? 0 : adresse.hashCode());
    result = prime * result + (description == null ? 0 : description.hashCode());
    result = prime * result + (nom == null ? 0 : nom.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Magasin other = (Magasin) obj;
    if (adresse == null) {
      if (other.adresse != null) {
        return false;
      }
    } else if (!adresse.equals(other.adresse)) {
      return false;
    }
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (nom == null) {
      if (other.nom != null) {
        return false;
      }
    } else if (!nom.equals(other.nom)) {
      return false;
    }
    return true;
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
