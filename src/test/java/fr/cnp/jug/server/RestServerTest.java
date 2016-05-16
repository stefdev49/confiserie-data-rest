package fr.cnp.jug.server;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import fr.cnp.jug.domain.DomainConfiguration;
import fr.cnp.jug.repositories.RepositoryConfiguration;

/**
 * Created by stef on 14/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DomainConfiguration.class, RepositoryConfiguration.class })
@WebAppConfiguration
public class RestServerTest {
	private static final String POST_MAGASIN_JSON = "{ \"nom\": \"QK Confiserie\", \"description\": \"Confiseries anglaises\", \"adresse\": { \"rue\": \"9 rue Saint-Etienne\", \"codePostal\": \"49000\", \"ville\": \"ANGERS\"} }";

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void postMagasinOK() {
        given().contentType(ContentType.JSON).body(POST_MAGASIN_JSON).post("/magasins").then().statusCode(201);
    }
}
