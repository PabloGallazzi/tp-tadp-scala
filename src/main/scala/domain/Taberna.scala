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

}
