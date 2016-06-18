package domain

/**
  * Created by Mariano on 13/6/2016.
  */
abstract class Manipulable(val valor: Int, val prioridad: Int) extends Item {

  def getCantidadDeManos: Int

  def quitarManipulables[T<:Item](listaItems: List[T]): List[T] ={
    val manipulables = listaItems
    manipulables.count(x => x.isInstanceOf[Manipulable]) match {
      case 2 =>
        if (this.getCantidadDeManos == 2) listaItems.filter(x => !x.isInstanceOf[Manipulable])
        else listaItems.filter(x => x != manipulables.head)
      case 1 =>
        if (this.getCantidadDeManos == 2) listaItems.filter(x => x != manipulables.head)
        else manipulables
      case 0 => manipulables
    }
  }
}

case object espadaDeLaVida extends Manipulable(100, 1){
  def getCantidadDeManos: Int = 1
  override def incrementarFuerza(heroe:Heroe): (Double=>Double) = {x=> heroe.getHP}

}

case object palitoMagico extends Manipulable(20, 2) {
  def getCantidadDeManos: Int = 1
  override def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=> x+20}
  override def sosEquipable(heroe:Heroe): Boolean=  heroe.sosMago

}


