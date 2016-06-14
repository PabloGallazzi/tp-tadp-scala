package domain

/**
  * Created by Mariano on 11/6/2016.
  */
trait Item {

  def incrementarFuerza(heroe:Heroe): (Double=>Double)
  def incrementarVelocidad(heroe:Heroe):(Double=>Double)
  def incrementarInteligenia(heroe:Heroe):(Double=>Double)
  def incrementarVida(heroe:Heroe):(Double=>Double)
  def sosEquipable(heroe:Heroe): Boolean
  def getValor: Int

}