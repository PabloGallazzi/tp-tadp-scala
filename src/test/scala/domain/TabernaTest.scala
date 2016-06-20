package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class TabernaTest extends BaseTest{

  @Test
  def `test_la_taberna_no_es_realizable`() = {
    val tarea: Tarea = new Tarea(
      { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
        equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 100
      }, { heroe => heroe.trabajo match {
        case Some(Mago) => 10
        case _ => 20
      }
      }, { heroe => heroe.cambiarDeTrabajo(Some(Mago)) })
    val tarea2: Tarea = new Tarea(
      { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
        equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 10
      }, { heroe => heroe.trabajo match {
        case Some(Mago) => 10
        case _ => 20
      }
      }, { heroe => heroe.cambiarDeTrabajo(Some(Guerrero)) })
    val mision: Mision = new Mision(List(tarea, tarea2), new masOroParaElEquipo(100))
    val taberna: Taberna = new Taberna(List(mision))
    assert(taberna.entrenar(equipo, { (equipo1, equipo2) => equipo1.oro > equipo2.oro }) == equipo)
  }

  @Test
  def `test_la_taberna_es_realizable`() = {
    val tarea: Tarea = new Tarea(
      { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
        equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 10
      }, { heroe => heroe.trabajo match {
        case Some(Mago) => 10
        case _ => 20
      }
      }, { heroe => heroe.cambiarDeTrabajo(Some(Mago)) })
    val tarea2: Tarea = new Tarea(
      { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
        equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 10
      }, { heroe => heroe.trabajo match {
        case Some(Mago) => 10
        case _ => 20
      }
      }, { heroe => heroe.cambiarDeTrabajo(Some(Guerrero)) })
    val mision: Mision = new Mision(List(tarea, tarea2), new masOroParaElEquipo(100))
    val taberna: Taberna = new Taberna(List(mision))
    assert(taberna.entrenar(equipo, { (equipo1, equipo2) => equipo1.oro > equipo2.oro }) != equipo)
  }

}
