package domain

/**
  * Created by Mariano on 11/6/2016.
  */
abstract class TipoTrabajo( val incHP:Double, val incFz:Double, val incVel:Double, val incInt:Double ) {
  def statPrincipal(heroe: Heroe):Double
}

case object Guerrero extends TipoTrabajo(10,15, 0,-10) {
  def statPrincipal(heroe: Heroe): Double = heroe.getFuerza
}

case object Mago extends TipoTrabajo(-20,0,0,20){
  def statPrincipal(heroe: Heroe): Double = heroe.getInteligencia
}

case object Ladron extends TipoTrabajo (-5,0,10,0){
  def statPrincipal(heroe: Heroe): Double = heroe.getVelocidad
}




