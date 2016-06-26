package domain



/**
  * Created by Mariano on 15/6/2016.
  */
case class Mision(tareas: List[Tarea],
                  recompensa: Recompensa) {


  def realizarsePor(equipo: Equipo): Option[Resultado] = {
    def resultado: Resultado = realizarTareas(equipo, tareas)

    Some(resultado).map({
      case Fracaso(tarea, _) => Fracaso(tarea, equipo)
      case Exito(equipoResultado) => Exito(recompensa.darRecompensaAEquipo(equipoResultado))
    })
/*
    resultado match {
      case Fracaso(tarea, _) => Some(Fracaso(tarea, equipo))
      case Exito(equipoResultado) => Some(Exito(recompensa.darRecompensaAEquipo(equipoResultado)))
    }*/
  }


  private def realizarTareas(equipo: Equipo, tareasARealizar: List[Tarea]): Resultado = {

    def actualizarResultado(someEquipo: Option[Equipo], resultadoAnterior: Resultado, tarea: Tarea):Resultado = {
      resultadoAnterior match {
        case f:Fracaso => f
        case e:Exito =>
          if (someEquipo.isDefined) new Exito(someEquipo.get) else new Fracaso(tarea,equipo)
      }
    }

    tareasARealizar.foldLeft[Resultado](new Exito(equipo))((r,t)=> actualizarResultado(t.realizarPorEquipo(equipo),r,t))

 //Opcion con recursividad
/*
    tareasARealizar match {
      case Nil => new Exito(equipo)
      case headTarea :: tailTareas => {
        def nuevoEquipoOrNone: Option[Equipo] = headTarea.realizarPorEquipo(equipo)
        nuevoEquipoOrNone match {
          case None => new Fracaso(headTarea, equipo)
          case Some(_) => realizarTareas(nuevoEquipoOrNone.get, tailTareas)
        }
      }
    }*/
  }

}

