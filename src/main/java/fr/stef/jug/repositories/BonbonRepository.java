package fr.stef.jug.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.stef.jug.domain.Bonbon;
import fr.stef.jug.domain.Magasin;

/**
 * Repository des bonbons.
 * 
 * @author stef
 *
 */
public interface BonbonRepository extends CrudRepository<Bonbon, Long> {

  /**
   * Renvoie un bonbon sélectionné par son nom
   * 
   * @param nom
   * @return
   */
  Magasin findByNom(@Param("nom") String nom);
}
