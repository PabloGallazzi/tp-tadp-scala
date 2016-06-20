package domain

/**
  * Created by Mariano on 15/6/2016.
  */
case class Mision(tareas: List[Tarea],
                  recompensa: Recompensa) {

  def realizarsePor(equipo: Equipo): Resultado = {
    def resultado: Resultado = realizarTareas(equipo, tareas)
    resultado match {
      //TODO: Preguntar si lo que se quiere es el equipo original, creo que si!
      case Fracaso(tarea, equipoResultado) => Fracaso(tarea, equipo)
      case Exito(equipoResultado) => Exito(recompensa.darRecompensaAEquipo(equipoResultado))
    }
  }

  private def realizarTareas(equipo: Equipo, tareasARealizar: List[Tarea]): Resultado = {
    tareasARealizar match {
      case Nil => new Exito(equipo)
      case headTarea :: tailTareas => {
        def nuevoEquipoOrNone: Option[Equipo] = headTarea.realizarPorEquipo(equipo)
        nuevoEquipoOrNone match {
          case None => new Fracaso(headTarea, equipo)
          case Some(_) => realizarTareas(nuevoEquipoOrNone.get, tailTareas)
        }
      }
    }
  }

}