package fr.stef.jug.domain;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MagasinTest {

  private static final String DESCRIPTION = "description";
  private static final String NOM = "nom";
  
  @Test
  public void testEqualsNomDescription() throws Exception {
    Magasin m1 = buildMagasin();
    Magasin m2 = buildMagasin();
    
    assertThat(m1.equals(m2)).isTrue();
  }
  
  @Test
  public void testNonEqualsAdresseNull() throws Exception {
    Magasin m1 = buildMagasin();
    m1.setAdresse(new Adresse());
    Magasin m2 = buildMagasin();
    assertThat(m1.equals(m2)).isFalse();
  }

  @Test
  public void testNonEqualsNomDescriptionNull() throws Exception {
    Magasin m1 = buildMagasin();
    
    assertThat(m1.equals(null)).isFalse();
  }

  private Magasin buildMagasin() {
    Magasin m1=new Magasin();
    m1.setNom(NOM);
    m1.setDescription(DESCRIPTION);
    return m1;
  }

}
