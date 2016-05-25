package domain

/**
  * Created by pgallazzi on 13/5/16.
  */

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Persona_Test {
  var persona: Persona = null

  @Before
  def setup() = {
    persona = new Persona(1)
  }

  @Test
  def cumpliAnio_test() = {
    persona.cumpliAnio
    assertEquals(2, persona.edad)
  }

  @Test
  def sosHumano_test() = {
    assertEquals(true, persona.sosHumano)
  }

  @Test
  def sosBebe_test() =
    assertEquals(true,persona.sosBebe)
}
