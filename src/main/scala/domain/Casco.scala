package domain

/**
  * Created by Mariano on 13/6/2016.
  */
abstract class Casco(val valor: Int, val prioridad:Int) extends Item {


}

case object vinchaDelBufaloDeAgua extends Casco(25,2){
  override def incrementarFuerza(heroe:Heroe): (Double=>Double) = this.incrementarStatsMenosInteligencia(heroe)
  override def incrementarVelocidad(heroe:Heroe):(Double=>Double) = this.incrementarStatsMenosInteligencia(heroe)
  override def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=> if(heroe.fuerza > heroe.inteligencia) x+30 else x}
  override def incrementarVida(heroe:Heroe):(Double=>Double) = incrementarStatsMenosInteligencia(heroe)
  override def sosEquipable(heroe:Heroe): Boolean = heroe.trabajo.eq(None)
  def incrementarStatsMenosInteligencia(heroe: Heroe):(Double=>Double) = {x=> if(heroe.fuerza > heroe.inteligencia) x else x+10 }

}
