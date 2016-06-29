package domain

/**
  * Created by Mariano on 17/6/2016.
  */
class Taberna(misiones: List[Mision]) {

  def elegirMision(equipo: Equipo, misiones: List[Mision], criterio: ((Equipo, Equipo) => Boolean)): Option[Mision] = {
    misiones match {
      case headOne :: headTwo :: tail => if (criterio(headOne.realizarsePor(equipo).equipo, headTwo.realizarsePor(equipo).equipo)) {
        Some(headOne)
      } else {
        Some(headTwo)
      }
      case headOne :: Nil => Some(headOne)
      case Nil => None
    }
  }

  def entrenar(equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Equipo = {
    realizarMisiones(misiones, equipo, criterio)
  }

  private def realizarMisiones(misiones: List[Mision], equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Equipo = {
    misiones.sortWith((m1, m2) => criterio(m1.realizarsePor(equipo).equipo, m2.realizarsePor(equipo).equipo)) match {
      case Nil => equipo
      case headOne :: tail => headOne.realizarsePor(equipo).map(equipo => realizarMisiones(tail, equipo, criterio)).equipo
    }
  }

}
