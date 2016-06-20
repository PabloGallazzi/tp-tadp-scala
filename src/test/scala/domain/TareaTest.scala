package domain

import org.junit.{Before, Test}

/**
  * Created by pgallazzi on 20/6/16.
  */
class TareaTest {

  @Before
  val tarea: Tarea = new Tarea(
    { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
      equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 10
    }, { heroe => heroe.trabajo match {
      case Some(Mago) => 10
      case _ => 20
    }
    }, { heroe => heroe.cambiarDeTrabajo(Some(Mago)) })
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
