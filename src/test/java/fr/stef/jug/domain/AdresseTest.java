package fr.stef.jug.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AdresseTest {

  private static final String CODE_POSTAL = "codePostal";
  private static final String VILLE = "ville";
  private static final String RUE = "rue";

  @Test
  public void testEquals() throws Exception {
    Adresse adr1 = new Adresse();
    adr1.setRue(RUE);
    adr1.setVille(VILLE);
    adr1.setCodePostal(CODE_POSTAL);

    Adresse adr2 = new Adresse();
    adr2.setRue(RUE);
    adr2.setVille(VILLE);
    adr2.setCodePostal(CODE_POSTAL);

    assertThat(adr1.equals(adr2)).isTrue();
  }

  @Test
  public void testEqualsNull() throws Exception {
    Adresse adr1 = new Adresse();
    adr1.setRue(RUE);
    adr1.setVille(VILLE);
    adr1.setCodePostal(CODE_POSTAL);

    assertThat(adr1.equals(null)).isFalse();
  }

}
