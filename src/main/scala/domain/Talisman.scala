package domain


/**
  * Created by Mariano on 13/6/2016.
  */
abstract class Talisman(val valor: Int) extends Item{
    def incrementarFuerza(heroe:Heroe): (Double=>Double)
    def incrementarVelocidad(heroe:Heroe):(Double=>Double)
    def incrementarInteligenia(heroe:Heroe):(Double=>Double)
    def incrementarVida(heroe:Heroe):(Double=>Double)
    def sosEquipable(heroe:Heroe): Boolean
    def getValor: Int = this.valor
  }

case object talismanDelMinimalismo extends Talisman(50){
    def incrementarFuerza(heroe:Heroe): (Double=>Double) = {x=> x}
    def incrementarVelocidad(heroe:Heroe):(Double=>Double) = {x=> x}
    def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=> x}
    def incrementarVida(heroe:Heroe):(Double=>Double) = {x=> x+50-heroe.getElementosEquipados.size*10}
    def sosEquipable(heroe:Heroe): Boolean = true
  }

case object talismanMaldito extends Talisman(80) {
    def incrementarFuerza(heroe: Heroe): (Double => Double) = { x => x*(-1000) }
    def incrementarVelocidad(heroe: Heroe): (Double => Double) = { x => x*(-1000) }
    def incrementarInteligenia(heroe: Heroe): (Double => Double) = { x => x*(-1000) }
    def incrementarVida(heroe: Heroe): (Double => Double) = { x => x*(-1000) }
    def sosEquipable(heroe: Heroe): Boolean = true
  }

case object talismanDeDedicacion extends Talisman(100) {

    def incrementarFuerza(heroe:Heroe): (Double=>Double) = {x=> heroe.statPrincipal*1.1}
    def incrementarVelocidad(heroe:Heroe):(Double=>Double) = {x=> heroe.statPrincipal*1.1}
    def incrementarInteligenia(heroe:Heroe):(Double=>Double) = {x=> heroe.statPrincipal*1.1}
    def incrementarVida(heroe:Heroe):(Double=>Double) = {x=> heroe.statPrincipal*1.1}
    def sosEquipable(heroe:Heroe): Boolean = true

  }



