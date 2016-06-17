package domain


/**
  * Created by Mariano on 13/6/2016.
  */
abstract class Talisman(val valor: Int, val prioridad:Int) extends Item{

  }

case object talismanDelMinimalismo extends Talisman(50,2){
    override def incrementarVida(heroe:Heroe):(Double=>Double) = {x=> x+50-heroe.getElementosEquipados.size*10}

  }

case object talismanMaldito extends Talisman(80,0) {
  override def incrementarFuerza(heroe: Heroe): (Double => Double) = { x => 1 }
  override def incrementarVelocidad(heroe: Heroe): (Double => Double) = { x => 1 }
  override def incrementarInteligenia(heroe: Heroe): (Double => Double) = { x => 1 }
  override def incrementarVida(heroe: Heroe): (Double => Double) = { x => 1 }

  }

case object talismanDeDedicacion extends Talisman(100,2) {

  override def incrementarFuerza(heroe:Heroe): (Double=>Double) = {x=> x+ heroe.statPrincipal*0.1}
  override def incrementarVelocidad(heroe:Heroe):(Double=>Double) = {x=>x+ heroe.statPrincipal*1.1}
  override def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=>x+ heroe.statPrincipal*0.1}
  override def incrementarVida(heroe:Heroe):(Double=>Double) = {x=>x+ heroe.statPrincipal*0.1}

  }



