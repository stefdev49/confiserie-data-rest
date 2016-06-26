package fr.stef.jug.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.stef.jug.domain.Magasin;

/**
 * Repository des magasins.
 * 
 * @author stef
 *
 * @RepositoryRestResource(path = "magasins") permet de fixer le nom de la ressource si le nom par défaut n'est
 * pas celui désiré. Par défaut nom de la classe du domaine exposé par le repository et suffixé par 's'.
 */
@RepositoryRestResource(path = "magasins")
public interface MagasinRepository extends PagingAndSortingRepository<Magasin, Long>, MagasinRepositoryCustom {

  /**
   * recherche un magasin par son nom. L'attribut servant à la recherche existe dans le modèle objet et
   * spring-data fait le lien automatiquement. Le service est exposé sous : [ressource]/search/findByNom?nom=
   * exemple:
   * http://localhost:7076/api/magasin/search/findByNom?nom=QK Confiserie
   * 
   * @param nom
   * @return le Magasin qui porte exactement le nom passé en entrée
   */
  Magasin findByNom(@Param("nom") String nom);
}
