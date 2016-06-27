package fr.stef.jug.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.stef.jug.domain.Magasin;
import fr.stef.jug.repositories.MagasinRepository;

/**
 * Controller implémentant l'appel REST de findByTexte.
 * 
 * @author stef
 *
 */
@RepositoryRestController
public class SearchByTexteController  implements ResourceProcessor<RepositorySearchesResource> {

  @Autowired
  MagasinRepository repository;

  /**
   * Renvoie la liste des magasins qui correspondent au texte passé en paramètre.
   * @param texte texte recherché dans les données des magasins
   * @param assembler composnat servant à encapsuler les netités trouvées en ressources HATEOAS
   * @return une réponse HTTP contenant la liste des magasins trouvés
   */
  @ResponseBody
  @RequestMapping(value = "/magasins/search/findByTexte", method = GET)
  public ResponseEntity<?> findByTexte(@RequestParam(value = "texte", required = true) String texte,
      PersistentEntityResourceAssembler assembler) {
    
    // on récupère le résultat
    List<Magasin> magasins = (List<Magasin>) repository.findAll();
    
    // puis on le parcourt avec une stream, mappant chaque élément comme une ressource HATEOAS
    // et en finalisant le tout comme une liste
    Resources<PersistentEntityResource> responseBody = new Resources<PersistentEntityResource>(
        magasins.stream().map(assembler::toResource).collect(Collectors.toList()));
    return ResponseEntity.ok(responseBody);
  }

  /**
   * Ajoute le lien vers la recherche par texte aux liens de recherche existants.
   */
  @Override
  public RepositorySearchesResource process(RepositorySearchesResource resource) {
    final String search = resource.getId().getHref();
    final Link findByTexte = new Link(search + "findByTexte{?texte}").withRel("findByTexte");
    resource.add(findByTexte);
    return resource;
  }

}
