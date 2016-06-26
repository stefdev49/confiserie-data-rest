package fr.stef.jug.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import fr.stef.jug.domain.Magasin;

/**
 * Interface définissant les méthodes personnalisées utilisées par le repository Magasin.
 * 
 * @author stef
 *
 */
@FunctionalInterface
public interface MagasinRepositoryCustom {
  
  /**
   * Recherche textuelle de magasins 
   * @param texte
   * @return La liste des magasins relative au texte passé en entrée
   */
  List<Magasin> findByTexte(@Param("texte") String texte);
}
