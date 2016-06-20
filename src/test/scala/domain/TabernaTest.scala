package domain

import org.junit.{Before, Test}

/**
  * Created by pgallazzi on 20/6/16.
  */
class TabernaTest {

  @Before
  val stats: Stats = new Stats(10, 5, 6, 9)
  val stats2: Stats = new Stats(12, 5, 6, 9)
  val stats3: Stats = new Stats(11, 5, 6, 9)
  val stats4: Stats = new Stats(19, 5, 6, 9)
  val heroe: Heroe = new Heroe(stats, Some(Guerrero))
  val heroe2: Heroe = new Heroe(stats2, Some(Guerrero))
  val heroe3: Heroe = new Heroe(stats3, Some(Guerrero))
  val heroe4: Heroe = new Heroe(stats4, Some(Mago))
  val equipo: Equipo = new Equipo("EquipoSaraza", List(heroe, heroe2, heroe3))



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
