package domain

/**
  * Created by Mariano on 11/6/2016.
  */
abstract class TipoTrabajo( val incHP:Double, val incFz:Double, val incVel:Double, val incInt:Double ) {
  def statPrincipal:Double
}

case class Guerrero (heroe: Heroe) extends TipoTrabajo(10,15, 0,-10) {
  def statPrincipal: Double = heroe.getFuerza
}

case class Mago(heroe: Heroe) extends TipoTrabajo(-20,0,0,20){
  def statPrincipal: Double = heroe.getInteligencia
}

case class Ladron(heroe: Heroe) extends TipoTrabajo (-5,0,10,0){
  def statPrincipal: Double = heroe.getVelocidad
}




