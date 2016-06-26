package fr.stef.jug.server;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.response.MockMvcResponse;

import fr.stef.jug.domain.DomainConfiguration;
import fr.stef.jug.repositories.RepositoryConfiguration;

/**
 * test d'intégration du serveur REST.
 * 
 * Created by stef on 14/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DomainConfiguration.class, RepositoryConfiguration.class })
@WebAppConfiguration
public class RestServerTest {
  private static final String POST_MAGASIN_JSON = "{ \"nom\": \"Une autre Confiserie\", \"description\": \"Confiseries géniales\", \"adresse\": { \"rue\": \"19 rue du test\", \"codePostal\": \"99999\", \"ville\": \"TESTVILLE\"} }";

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
  
  /**
   * test get d'un item par son nom, avec un assert sur le contenu d'un des
   * attributs retournés.
   */
  @Test
  public void searchByNomOK() {
    // http://localhost:7076/api/magasins/search/findByNom?nom=QK Confiserie
    // on doit avoir "description": "Confiseries anglaises" en retour
    given().get("/magasins/search/findByNom?nom=QK Confiserie").then().statusCode(200).body("description",
        new ResponseAwareMatcher<MockMvcResponse>() {
      public Matcher<?> matcher(MockMvcResponse response) {
        return equalTo("Confiseries anglaises");
      }
    });
  }
  
  /**
   * test get d'un item par texte, avec un assert sur le contenu d'un des
   * attributs retournés.
   */
  @Test
  public void getSearchByTexteURL() {
    // http://localhost:7076/api/magasins/search/
    // on doit avoir "description": "Confiseries anglaises" en retour
    given().get("/api/magasins/search/").then().statusCode(200).body("description",
        new ResponseAwareMatcher<MockMvcResponse>() {
      public Matcher<?> matcher(MockMvcResponse response) {
        return equalTo("magasin mock");
      }
    });
  }

  /**
   * test get d'un item par texte, avec un assert sur le contenu d'un des
   * attributs retournés.
   */
  @Test
  public void searchByTexteOK() {
    // http://localhost:7076/api/magasins/search/findByTexte?texte=confiserie
    // on doit avoir "description": "Confiseries anglaises" en retour
    given().get("/api/magasins/search/findByTexte?texte=confiserie").then().statusCode(200).body("description",
        new ResponseAwareMatcher<MockMvcResponse>() {
          public Matcher<?> matcher(MockMvcResponse response) {
            return equalTo("magasin mock");
          }
        });
  }
}
