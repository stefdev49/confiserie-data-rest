package fr.stef.jug.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.stef.jug.domain.Magasin;

/**
 * Repository des magasins.
 * 
 * @author stef
 *
 * @RepositoryRestResource(path = "magasin") permet de fixer le nom de la ressource si le nom par défaut n'est
 * pas celui désiré. Par défaut nom de la classe du domaine exposé par le repository et suffixé par 's'.
 */
@RepositoryRestResource(path = "magasins")
public interface MagasinRepository extends PagingAndSortingRepository<Magasin, Long> {

  /**
   * recherche un magasin par son nom. L'attribut servant à la recherche existe dans le modèle objet et
   * spring-data fait le lien automatiquement. Le service est exposé sous : [ressource]/search/findByNom?nom=
   * exemple:
   * http://localhost:7076/api/magasin/search/findByNom?nom=QK Confiserie
   * 
   * @param nom
   * @return
   */
  Magasin findByNom(@Param("nom") String nom);
  
  
  /**
   * Recherche textuelle de magasins 
   * @param texte
   * @return La liste des magasins relative au texte passé en entrée
   */
  //List<Magasin> findByTexte(@Param("texte") String texte);
}
