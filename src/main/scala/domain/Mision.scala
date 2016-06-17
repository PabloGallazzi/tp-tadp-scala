package domain
import util.control.Breaks._
/**
  * Created by Mariano on 15/6/2016.
  */
abstract class Mision( val tareas: List[Tarea]) {

  def sosRealizablePorEquipo(equipo: Equipo) =
    tareas.count(t => t.sosRealizablePor(equipo)) > 0

  def teVaARealizarEquipo(equipo: Equipo):Any= {
    val unEquipo= equipo.getCopia
    terminoODondeQuedo(equipo) match{
      case Some((tarea,estadoEquipo)) => {
        equipo.restaurarEstadoOriginal(unEquipo)
        (tarea,estadoEquipo)
      }
      case Some(unEquipo) => unEquipo
      case None => equipo
    }

  }


  private def terminoODondeQuedo(equipo: Equipo): Option[Any] = {
    var option: Option[Any] = None
    breakable {
      for (t <- tareas) {
        if (!t.sosRealizablePor(equipo)) {
          break
          option = Some((t, equipo))
        }
        else {
          equipo.masAptoParaTarea(t)
          option = Some(equipo)
        }

      }
    }
    option
  }



}

