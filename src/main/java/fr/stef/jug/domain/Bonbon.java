package fr.stef.jug.domain;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Description d'un bonbon.
 * 
 * @author stef
 *
 */
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((nom == null) ? 0 : nom.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bonbon other = (Bonbon) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (nom == null) {
      if (other.nom != null)
        return false;
    } else if (!nom.equals(other.nom))
      return false;
    return true;
  }
}
