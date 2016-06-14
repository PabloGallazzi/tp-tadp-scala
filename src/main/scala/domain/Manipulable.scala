package domain

/**
  * Created by Mariano on 13/6/2016.
  */
abstract class Manipulable(val valor: Int) extends Item {
  def getCantidadDeManos(heroe:Heroe): Int
  def incrementarFuerza(heroe:Heroe): (Double=>Double)
  def incrementarVelocidad(heroe:Heroe):(Double=>Double)
  def incrementarInteligenia(heroe:Heroe):(Double=>Double)
  def incrementarVida(heroe:Heroe):(Double=>Double)
  def sosEquipable(heroe:Heroe): Boolean
  def getValor: Int = this.valor

}

case object espadaDeLaVida extends Manipulable(100){
  def getCantidadDeManos(heroe:Heroe): Int = 1
  def incrementarFuerza(heroe:Heroe): (Double=>Double) = {x=> heroe.getHP}
  def incrementarVelocidad(heroe:Heroe):(Double=>Double) = {x=> x}
  def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=> x}
  def incrementarVida(heroe:Heroe):(Double=>Double) = {x=> x}
  def sosEquipable(heroe:Heroe): Boolean= true
}

case object palitoMagico extends Manipulable(20) {
  def getCantidadDeManos(heroe:Heroe): Int = 1
  def incrementarFuerza(heroe:Heroe): (Double=>Double) = {x=> x}
  def incrementarVelocidad(heroe:Heroe):(Double=>Double) = {x=> x}
  def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=> x+20}
  def incrementarVida(heroe:Heroe):(Double=>Double) = {x=> x}
  def sosEquipable(heroe:Heroe): Boolean=  heroe.getTrabajo.getOrElse(Nil).isInstanceOf[Mago]


}


