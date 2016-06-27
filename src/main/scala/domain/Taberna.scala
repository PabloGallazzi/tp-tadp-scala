package domain

/**
  * Created by Mariano on 17/6/2016.
  */
class Taberna(misiones: List[Mision]) {

  def elegirMision(equipo: Equipo, mision: Mision, otraMision: Mision, criterio: ((Equipo, Equipo) => Boolean)): Mision = {
    if (criterio(mision.realizarsePor(equipo).map(_.equipo).get, otraMision.realizarsePor(equipo).map(_.equipo).get)) {
      return mision
    }
    otraMision
  }

  def entrenar(equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Equipo = {
    realizarMisiones(misiones, equipo, criterio).get.equipo
  }

  private def realizarMisiones(misiones: List[Mision], equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Option[Resultado] = {

    /** Ver como hacer con orden superior sin recursividad **/
    misiones.sortWith((m1, m2) => criterio(m1.realizarsePor(equipo).get.equipo, m2.realizarsePor(equipo).get.equipo)) match {
      case Nil => Some(Exito(equipo))
      case headOne :: tail => headOne.realizarsePor(equipo).get match {
        case Fracaso(tarea, equipoFinal) => Some(new Fracaso(tarea, equipoFinal))
        case Exito(equipoDespuesDeTarea) => realizarMisiones(tail, equipoDespuesDeTarea, criterio)
      }
    }
  }

}
