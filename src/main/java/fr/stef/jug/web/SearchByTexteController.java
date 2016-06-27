package fr.stef.jug.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
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
public class SearchByTexteController {

  @Autowired
  MagasinRepository repository;

  @ResponseBody
  @RequestMapping(value = "/magasins/search/findByTexte", method = GET)
  public ResponseEntity<?> findByTexte(@RequestParam(value = "texte", required = true) String texte,
      PersistentEntityResourceAssembler assembler) {
    List<Magasin> magasins = (List<Magasin>) repository.findAll();
    Resources<PersistentEntityResource> responseBody = new Resources<PersistentEntityResource>(
        magasins.stream().map(assembler::toResource).collect(Collectors.toList()));
    return ResponseEntity.ok(responseBody);
  }

}
