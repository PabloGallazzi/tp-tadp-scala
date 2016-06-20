package domain

/**
  * Created by Mariano on 19/6/2016.
  */
abstract class Resultado {

}

class Exito(val equipo: Equipo) extends Resultado

class Fracaso(val tarea:Tarea, val equipo: Equipo) extends Resultado

