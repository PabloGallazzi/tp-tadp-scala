package domain

/**
  * Created by pgallazzi on 20/6/16.
  */
abstract class Resultado(val equipo: Equipo)

case class Exito(override val equipo: Equipo) extends Resultado(equipo = equipo)

case class Fracaso(tarea: Tarea, override val equipo: Equipo) extends Resultado(equipo = equipo)