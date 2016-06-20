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
    def mejorMision: Mision = obtenerMejorMision(equipo, criterio, misones)
    mejorMision match {
      case null => Exito(equipo)
      case _ => {
        def resultado: Resultado = mejorMision.realizarsePor(equipo)
        resultado match {
          case Fracaso(tarea, equipo) => new Fracaso(tarea, equipo)
          case Exito(equipoDespuesDeTarea) => realizarMisiones(misiones.filter(mision => mision != mejorMision), equipoDespuesDeTarea, criterio)
        }
      }
    }
  }

  private def obtenerMejorMision(equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean), misiones: List[Mision]): Mision = {
    misiones match {
      case Nil => null
      case head :: Nil => head
      case headOne :: headTwo :: tail => obtenerMejorMision(equipo, criterio, elegirMision(equipo, headOne, headTwo, criterio) :: tail)
    }
  }

}
