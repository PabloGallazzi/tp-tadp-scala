package domain

/**
  * Created by pgallazzi on 20/6/16.
  */
abstract class Resultado

//TODO: Ver si el default es val, en case class si, pero acá no se.
case class Exito(equipo: Equipo) extends Resultado

case class Fracaso(tarea: Tarea, equipo: Equipo) extends Resultado