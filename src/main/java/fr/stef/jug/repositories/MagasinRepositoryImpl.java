package fr.stef.jug.repositories;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import fr.stef.jug.domain.Magasin;

/**
 * Classe d'implémentation des comportement spécifiques définis dans
 * MagasinRepositoryCustom. Pour être utilisé comme implémentation de
 * l'extension XxxxRepositoryCustom, la classe <b>doit s'appeler
 * XxxxRepositoryImpl</b>. Custom -> Impl
 * 
 * @author stef
 *
 */
public class MagasinRepositoryImpl implements MagasinRepositoryCustom {
  /**
   * Logger de la classe d'implémentation
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(MagasinRepositoryImpl.class);

  @Autowired
  MagasinRepository magasins;

  @Override
  public List<Magasin> findByTexte(@Param("texte") String texte) {
    LOGGER.trace("Recherche par texte avec '{}'", texte);

    /*
     * on sélectionn les ids sur la base du texte donné, acutellement on simule
     * ça avec une liste en dur en attendant que la recherche soit créée
     */
    List<Long> ids = Arrays.asList(1L, 2L);
    LOGGER.trace("Les ids correspondants sont {}", ids);

    /*
     * on peut renvoyer maitnenant les magasins correspondants aux ids
     * sélectionnés
     */
    return magasins.findByIdIn(ids);
  }

}
