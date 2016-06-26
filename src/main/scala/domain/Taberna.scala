package domain

import scala.util.Try


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

  private def realizarMisiones(misiones: List[Mision], equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean)):Option[Resultado] = {

    /** Ver como hacer con orden superior sin recursividad**/
    def mejorMision: Option[Mision] = obtenerMejorMisionDadaUnaLista(equipo, criterio, misiones)
    mejorMision match {
      case None => Some(Exito(equipo))
      case _ => {
        def resultado: Resultado = mejorMision.flatMap(_.realizarsePor(equipo)).get
        resultado match {
          case Fracaso(tarea, equipoFinal) => Some(new Fracaso(tarea, equipoFinal))
          case Exito(equipoDespuesDeTarea) => realizarMisiones(misiones.filter(mision => mision != mejorMision.get), equipoDespuesDeTarea, criterio)
        }
      }
    }
  }


  private def obtenerMejorMisionDadaUnaLista(equipo: Equipo, criterio: ((Equipo, Equipo) => Boolean), misiones: List[Mision]): Option[Mision] = {

    misiones.sortWith((m1,m2)=> criterio(m1.realizarsePor(equipo).get.equipo, m2.realizarsePor(equipo).get.equipo)).headOption

    /*misiones match {
      case Nil => None
      case head :: Nil => Some(head)
      case headOne :: headTwo :: tail => obtenerMejorMisionDadaUnaLista(equipo, criterio, elegirMision(equipo, headOne, headTwo, criterio) :: tail)
    }*/
  }

}
