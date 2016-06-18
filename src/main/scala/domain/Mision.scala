package domain
import util.control.Breaks._
/**
  * Created by Mariano on 15/6/2016.
  */
abstract class Mision( val tareas: List[Tarea]) {

  def cobrarRecompenza(equipo: Equipo): Unit

  def sosRealizablePorEquipo(equipo: Equipo) =
    tareas.count(t => t.sosRealizablePor(equipo)) > 0

  def teVaARealizarEquipo(equipo: Equipo):Any= {
    val unEquipo= equipo.getCopia
    terminoODondeQuedo(equipo) match{
      case Some((tarea,estadoEquipo)) => {
        equipo.restaurarEstadoOriginal(unEquipo)
        (tarea,estadoEquipo)
      }
      case Some(e:Equipo) => {
        cobrarRecompenza(e)
        e
      }
      case _ => equipo
    }

  }



  private def terminoODondeQuedo(equipo: Equipo): Option[Any] = {
    var option: Option[Any] = None
    breakable {
      for (t <- tareas) {
        if (!t.sosRealizablePor(equipo)) {
          option = Some((t, equipo))
          break
        }
        else {
          t.teVaARealizar(equipo)
          option = Some(equipo)
        }

      }
    }
    option
  }

}

case object misionBase extends Mision(List(pelearContraMonstruo, forzarPuerta, robarTalisman)){

  def cobrarRecompenza(equipo: Equipo) =
    equipo.oro += 200

}

case object misionObtenerTalisman extends Mision(List(pelearContraMonstruo)) {
  def cobrarRecompenza(equipo: Equipo) =
    equipo.oro += 50
}

case object misionConquistarDungeon extends Mision(List(forzarPuerta,pelearContraMonstruo)){
  def cobrarRecompenza(equipo: Equipo) =
    equipo.oro += 100

}
