package fr.stef.jug.repositories;

import static org.assertj.core.api.Assertions.assertThat;

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

import fr.stef.jug.domain.Magasin;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfiguration.class)
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class MagasinRepositoryImplTest {


  @Autowired
  MagasinRepository repository;

  @Test
  public void listeConfiserieNonVide() {
    List<Magasin> actual = repository.findByTexte("confiserie");
    assertThat(actual).isNotNull().isNotEmpty();
  }

}
