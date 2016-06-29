package domain


/**
  * Created by Mariano on 15/6/2016.
  */
case class Mision(tareas: List[Tarea],
                  recompensa: Recompensa) {


  def realizarsePor(equipo: Equipo): Resultado = {
    def resultado: Resultado = realizarTareas(equipo, tareas)
    resultado.map({ equipo => recompensa.darRecompensaAEquipo(equipo) })
  }


  private def realizarTareas(equipo: Equipo, tareasARealizar: List[Tarea]): Resultado = {
    tareasARealizar.foldLeft[Resultado](new Exito(equipo))((r, t) => r.flatMap({ equipo => t.realizarPorEquipo(equipo) }))
  }

}

