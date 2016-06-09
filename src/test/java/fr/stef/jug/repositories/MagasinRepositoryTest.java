package fr.stef.jug.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import fr.stef.jug.domain.Adresse;
import fr.stef.jug.domain.Magasin;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = Application.class) => implique
// d'avoir un test 'serveur'
@ContextConfiguration(classes = RepositoryConfiguration.class)
public class MagasinRepositoryTest {

  @Autowired
  MagasinRepository repository;

  @Test
  public void qkConfiserieDoitEtrePresent() {
    Magasin qk = repository.findOne(1L);
    assertThat(qk).isNotNull();
  }

  @Test
  public void creationMagasinOk() {
    Magasin nouveau = new Magasin();

    nouveau.setNom("test");
    nouveau.setDescription("test de création");
    Adresse adresse = new Adresse();
    adresse.setRue("allée du test");
    adresse.setCodePostal("code postal");
    adresse.setVille("TDD");
    nouveau.setAdresse(adresse);

    Magasin actual = repository.save(nouveau);

    assertThat(actual).isNotNull();
    Magasin created = repository.findOne(actual.getId());
    assertThat(created).isNotNull();
    assertThat(created).isEqualTo(actual);
  }
}
