package fr.stef.jug.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.stef.jug.domain.Magasin;

/**
 * Repository des magasins.
 * 
 * @author stef
 *
 */
public interface MagasinRepository extends PagingAndSortingRepository<Magasin, Long> {

  /**
   * recherche un magasin par son nom
   * 
   * @param nom
   * @return
   */
  Magasin findByNom(@Param("nom") String nom);
}
