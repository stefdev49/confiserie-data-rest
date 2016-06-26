package fr.stef.jug.repositories;

import java.util.ArrayList;
import java.util.List;

import fr.stef.jug.domain.Magasin;

/**
 * Classe d'implémentation des comportement spécifiques définis dans MagasinRepositoryCustom.
 * Pour être utilisé comme implémentation de l'extension XxxxRepositoryCustom, la classe <b>doit s'appeler XxxxRepositoryImpl</b>.
 * Custom -> Impl
 * @author stef
 *
 */
public class MagasinRepositoryImpl implements MagasinRepositoryCustom {

  @Override
  public List<Magasin> findByTexte(String texte) {
    List<Magasin> result=new ArrayList<>();
    Magasin un=new Magasin();
    result.add(un);
    return result;
  }

}
