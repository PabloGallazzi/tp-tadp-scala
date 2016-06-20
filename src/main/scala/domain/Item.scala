package domain

/**
  * Created by Mariano on 11/6/2016.
  */
abstract class Item {

  val valor: Int
  val prioridad: Int

  def incrementarFuerza(heroe: Heroe): (Double => Double) = { x => x }

  def incrementarVelocidad(heroe: Heroe): (Double => Double) = { x => x }

  def incrementarInteligenia(heroe: Heroe): (Double => Double) = { x => x }

  def incrementarVida(heroe: Heroe): (Double => Double) = { x => x }

  def sosEquipable(heroe: Heroe): Boolean = true

}