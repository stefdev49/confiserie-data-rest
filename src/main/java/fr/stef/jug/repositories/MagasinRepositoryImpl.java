package fr.stef.jug.repositories;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;

import fr.stef.jug.domain.Magasin;

/**
 * Classe d'implémentation des comportement spécifiques définis dans MagasinRepositoryCustom.
 * Pour être utilisé comme implémentation de l'extension XxxxRepositoryCustom, la classe <b>doit s'appeler XxxxRepositoryImpl</b>.
 * Custom -> Impl
 * @author stef
 *
 */
public class MagasinRepositoryImpl implements MagasinRepositoryCustom {

  /**
   * Logger de la classe d'implémentation
   */
  Logger LOGGER=LoggerFactory.getLogger(MagasinRepositoryImpl.class);
  
  @Override
  public List<Magasin> findByTexte(@Param("texte") String texte) {
    LOGGER.trace("Recherche par texte avec '{}'", texte);
    
    List<Magasin> result=new ArrayList<>();
    Magasin un=new Magasin();
    un.setNom("mock");
    un.setDescription("magasin mock");
    result.add(un);
    return result;
  }

}
