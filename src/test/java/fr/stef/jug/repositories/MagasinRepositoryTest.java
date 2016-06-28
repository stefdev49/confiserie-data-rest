package fr.stef.jug.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.stef.jug.domain.Adresse;
import fr.stef.jug.domain.Magasin;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = Application.class) => implique
// d'avoir un test 'serveur'
@ContextConfiguration(classes = RepositoryConfiguration.class)
// ajout transaction
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class MagasinRepositoryTest {

  @Autowired
  MagasinRepository repository;
  
  @Test
  public void qkConfiserieDoitEtrePresent() {
    Magasin qk = repository.findOne(1L);
    assertThat(qk).isNotNull();
  }
  
  @Test
  public void findByNomQKConfiserie() {
    Magasin qk = repository.findByNom("QK Confiserie");
    assertThat(qk).isNotNull();
  }

  @Test
  public void findByTexte() {
    List<Magasin> actual = repository.findByTexte("Confiserie");
    assertThat(actual).isNotNull().isNotEmpty();
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

  @Test
  public void listeMagasinsParIds() {
    List<Long> magasinIdList=Arrays.asList(1L, 2L);
    List<Magasin> actual = repository.findByIdIn(magasinIdList);
    assertThat(actual).isNotNull().isNotEmpty();
    assertThat(actual.size()).isEqualTo(2);
  }
}
