package domain

/**
  * Created by Mariano on 13/6/2016.
  */
abstract class Casco(val valor: Int) extends Item {
  def incrementarFuerza(heroe:Heroe): (Double=>Double)
  def incrementarVelocidad(heroe:Heroe):(Double=>Double)
  def incrementarInteligenia(heroe:Heroe):(Double=>Double)
  def incrementarVida(heroe:Heroe):(Double=>Double)
  def sosEquipable(heroe:Heroe): Boolean
  def getValor: Int = this.valor
}