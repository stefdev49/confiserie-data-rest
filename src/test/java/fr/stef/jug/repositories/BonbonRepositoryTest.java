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

import fr.stef.jug.domain.Bonbon;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfiguration.class)
public class BonbonRepositoryTest {

  @Autowired
  BonbonRepository repository;

  @Test
  public void quernonDoitEtrePresent() {
    Bonbon quernon = repository.findOne(1L);
    assertThat(quernon).isNotNull();
    assertThat(quernon.getNom()).containsOnlyOnce("Quernon");
  }

  @Test
  public void creationBonbonOk() {
    Bonbon nouveau = new Bonbon();

    nouveau.setNom("test");
    nouveau.setDescription("test de création");

    Bonbon actual = repository.save(nouveau);

    assertThat(actual).isNotNull();

    Bonbon created = repository.findOne(actual.getId());
    assertThat(created).isNotNull();
    assertThat(created).isEqualTo(actual);
  }
}
