

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
    realizarMisiones(misiones, equipo, criterio)
  }

  private def realizarMisiones(misiones: List[Mision], equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)): Equipo = {
    mejorMisionDeLaLista(equipo, misiones, criterio).fold(equipo)(mision => mision.realizarsePor(equipo).map(equipo => realizarMisiones(misiones.filter(m => m != mision), equipo, criterio)).equipo)
  }

  def mejorMisionDeLaLista(equipo: Equipo, misiones: List[Mision], criterio: ((Equipo, Equipo) => Boolean)): Option[Mision] = {
    misiones.sortWith((m1, m2) => criterio(m1.realizarsePor(equipo).equipo, m2.realizarsePor(equipo).equipo)).headOption
  }

}
