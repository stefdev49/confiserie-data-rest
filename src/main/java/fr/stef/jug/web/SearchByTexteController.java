package fr.stef.jug.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.stef.jug.domain.Magasin;
import fr.stef.jug.repositories.MagasinRepository;

@RestController
@ExposesResourceFor(Magasin.class)
public class SearchByTexteController {

  @Autowired
  MagasinRepository repository;
  
  @RequestMapping(path = "/api/magasins/search/findByTexte", method = GET)
  List<Magasin> findByTexte(@RequestParam(value="texte", required=true) String texte) {
    return repository.findByTexte(texte);
  }

}
