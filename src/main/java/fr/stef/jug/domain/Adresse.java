package fr.stef.jug.domain;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Adresse postale.
 *
 * @author stef
 *
 */
@SuppressWarnings("serial")
@Entity
public class Adresse extends AbstractPersistable<Long> {

  private String rue;

  private String codePostal;

  private String ville;

  public String getRue() {
    return rue;
  }

  public void setRue(String rue) {
    this.rue = rue;
  }

  public String getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (codePostal == null ? 0 : codePostal.hashCode());
    result = prime * result + (rue == null ? 0 : rue.hashCode());
    result = prime * result + (ville == null ? 0 : ville.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Adresse other = (Adresse) obj;
    if (codePostal == null) {
      if (other.codePostal != null) {
        return false;
      }
    } else if (!codePostal.equals(other.codePostal)) {
      return false;
    }
    if (rue == null) {
      if (other.rue != null) {
        return false;
      }
    } else if (!rue.equals(other.rue)) {
      return false;
    }
    if (ville == null) {
      if (other.ville != null) {
        return false;
      }
    } else if (!ville.equals(other.ville)) {
      return false;
    }
    return true;
  }
}
