package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class MisionTest extends BaseTest{

  @Test
  def `test_la_mision_es_realizable`() = {
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
    def resultado: Resultado = mision.realizarsePor(equipo)
    assert(mision.realizarsePor(equipo).isInstanceOf[Exito])
    def exito: Exito = resultado.asInstanceOf[Exito]
    assert(exito.equipo != equipo)

  }

  @Test
  def `test_la_mision_no_es_realizable`() = {
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
    def resultado: Resultado = mision.realizarsePor(equipo)
    assert(mision.realizarsePor(equipo).isInstanceOf[Fracaso])
    def fracaso: Fracaso = resultado.asInstanceOf[Fracaso]
    assert(fracaso.tarea == tarea)
    assert(fracaso.equipo == equipo)
  }

}
