package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class TareaTest extends BaseTest{

  @Test
  def `test_la_tarea_devuelve_correctamente_el_calculador_para_heroe`() = {
    assert(tarea.realizarPorEquipo(equipo).isDefined)
  }

  @Test
  def `test_la_tarea_devuelve_none_cuando_no_puede_ser_realizada`() = {
    val tarea2: Tarea = new Tarea(
      { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
        equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 100
      }, { heroe => heroe.trabajo match {
        case Some(Mago) => 10
        case _ => 20
      }
      }, { heroe => heroe })
    assert(tarea2.realizarPorEquipo(equipo).isEmpty)
  }

}
