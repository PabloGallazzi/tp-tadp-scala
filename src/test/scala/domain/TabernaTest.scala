package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class TabernaTest extends BaseTest{

  @Test
  def `test_la_taberna_no_es_realizable`() = {
    val mision: Mision = new Mision(List(tareaNoRealizable), new masOroParaElEquipo(100))
    val taberna: Taberna = new Taberna(List(mision))
    assert(taberna.entrenar(equipo, { (equipo1, equipo2) => equipo1.oro > equipo2.oro }) == equipo)
  }

  @Test
  def `test_la_taberna_es_realizable`() = {
    val mision: Mision = new Mision(List(tarea, tarea), new masOroParaElEquipo(100))
    val taberna: Taberna = new Taberna(List(mision))
    assert(taberna.entrenar(equipo, { (equipo1, equipo2) => equipo1.oro > equipo2.oro }) != equipo)
  }

}
