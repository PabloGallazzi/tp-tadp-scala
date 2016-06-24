package domain

/**
  * Created by Mariano on 17/6/2016.
  */
class Taberna(misiones: List[Mision]) {

  def elegirMision(equipo: Equipo, mision: Mision, otraMision: Mision, criterio: ((Equipo, Equipo) => Boolean)): Mision = {
    if (criterio(mision.realizarsePor(equipo).equipo, otraMision.realizarsePor(equipo).equipo)) {
      return mision
    }
    otraMision
  }

  def entrenar(equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Equipo = {
    realizarMisiones(misiones, equipo, criterio).equipo
  }

  private def realizarMisiones(misones: List[Mision], equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Resultado = {
    def mejorMision: Option[Mision] = obtenerMejorMisionDadaUnaLista(equipo, criterio, misones)
    mejorMision match {
      case None => Exito(equipo)
      case _ => {
        def resultado: Resultado = mejorMision.get.realizarsePor(equipo)
        resultado match {
          case Fracaso(tarea, equipoFinal) => new Fracaso(tarea, equipoFinal)
          case Exito(equipoDespuesDeTarea) => realizarMisiones(misiones.filter(mision => mision != mejorMision.get), equipoDespuesDeTarea, criterio)
        }
      }
    }
  }

  private def obtenerMejorMisionDadaUnaLista(equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean), misiones: List[Mision]): Option[Mision] = {
    misiones match {
      case Nil => None
      case head :: Nil => Some(head)
      case headOne :: headTwo :: tail => obtenerMejorMisionDadaUnaLista(equipo, criterio, elegirMision(equipo, headOne, headTwo, criterio) :: tail)
    }
  }

}
