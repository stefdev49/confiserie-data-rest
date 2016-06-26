package fr.stef.jug.web;

import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * spring-data-rest n'ajoute pas automatiquement aux ressources de recherche les
 * méthodes 'custom' de repository car il est impossible de deviner comment
 * elles doivent être exportées (par exemple GET ou POST ou PUT ?).
 * 
 * Cette classe ser à ajouter un lien à la liste des liens de la recherche.
 * 
 * @author stef
 *
 */
@Component
public class SearchResourcesProcessor implements ResourceProcessor<RepositorySearchesResource> {

  @Override
  public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
    final String search = repositorySearchesResource.getId().getHref();
    // on ajoute cette URL uniquement pour magasins: TODO faire mieux pour ne
    // pas faire un test sur 'URL
    if (search.contains("/magasins/search")) {
      final Link findByTexte = new Link(search + "/findByTexte{?texte}").withRel("findByTexte");
      repositorySearchesResource.add(findByTexte);
    }
    return repositorySearchesResource;
  }
}
