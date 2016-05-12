package fr.cnp.jug.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import fr.cnp.jug.domain.Magasin;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, 
						 TransactionalTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class) => implique d'avoir une test 'serveur'
@ContextConfiguration(classes = RepositoryConfiguration.class)
public class MagasinRepositoryTest {

	@Autowired
	MagasinRepository repository;
	
	@Test
	public void qkConfiserieDoitEtrePresent() {
		Magasin qk=repository.findOne(1L);
		assertThat(qk).isNotNull();
	}
}
