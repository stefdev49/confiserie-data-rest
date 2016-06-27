package fr.stef.jug.server;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
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

/**
 * test d'intégration du serveur REST.
 * 
 * Created by stef on 14/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
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
    given().contentType(ContentType.JSON).body(POST_MAGASIN_JSON).post("/magasins").then().statusCode(HttpStatus.SC_CREATED);
  }
  
  /**
   * test get d'un item par son nom, avec un assert sur le contenu d'un des
   * attributs retournés.
   */
  @Test
  public void searchByNomOK() {
    // http://localhost:7076/api/magasins/search/findByNom?nom=QK Confiserie
    // on doit avoir "description": "Confiseries anglaises" en retour
    given().get("/magasins/search/findByNom?nom=QK Confiserie").then().statusCode(HttpStatus.SC_OK).body("description",
        new ResponseAwareMatcher<MockMvcResponse>() {
      public Matcher<?> matcher(MockMvcResponse response) {
        return equalTo("Confiseries anglaises");
      }
    });
  }
  
  /**
   * test recherche de la description du search dans les ressources.
   */
  @Test
  public void findByTexteURLOK() {
    // http://localhost:7076/api/magasins/search/
    // 
    given().get("/magasins/search/").then().statusCode(HttpStatus.SC_OK).body("_links.findByTexte.href",
        new ResponseAwareMatcher<MockMvcResponse>() {
      public Matcher<?> matcher(MockMvcResponse response) {
        return endsWith("/magasins/search/findByTexte{?texte}");
      }
    });
  }

  /**
   * test recherche d'un item par texte, avec un assert sur le contenu d'un des
   * attributs retournés.
   */
  @Test
  public void findByTexteOK() {
    // http://localhost:7076/api/magasins/search/findByTexte?texte=confiserie
    // on doit avoir "description": "Confiseries anglaises" en retour dans le 1er item renvoyé
    given().get("/magasins/search/findByTexte?texte=confiserie").then().statusCode(HttpStatus.SC_OK).body("_embedded.magasins[0].description",
        new ResponseAwareMatcher<MockMvcResponse>() {
          public Matcher<?> matcher(MockMvcResponse response) {
            return equalTo("Confiseries anglaises");
          }
        });
  }
}
