package domain

/**
  * Created by Mariano on 14/6/2016.
  */
class Tarea( var afectadorHeroe: (Heroe => Unit) = {x => x},var afectadorEquipo: (Equipo => Unit) = {x => x}) {



  def tePudoRealizar(heroe: Heroe) = ???

}
