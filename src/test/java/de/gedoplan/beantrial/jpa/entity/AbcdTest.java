package de.gedoplan.beantrial.jpa.entity;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AbcdTest
{
  @Test
  public void testAb() throws Exception
  {
    assertNotNull("b must not be null", QA.a.b);
  }

  @Test
  public void testAbc() throws Exception
  {
    assertNotNull("c must not be null", QA.a.b.c);
  }

  @Test
  public void testAbcd() throws Exception
  {
    assertNotNull("d must not be null", QA.a.b.c.d);
  }
}
