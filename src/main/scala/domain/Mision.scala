package domain

/**
  * Created by Mariano on 15/6/2016.
  */
case class Mision(tareas: List[Tarea],
                  recompensa: Recompensa) {

  def realizarsePor(equipo: Equipo): Option[Resultado] = {
    Some(realizarTareas(equipo, tareas))
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

