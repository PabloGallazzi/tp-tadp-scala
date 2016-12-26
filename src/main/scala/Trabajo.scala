

/**
  * Created by Mariano on 11/6/2016.
  */
abstract case class Trabajo(stats: Stats) {
  def statPrincipal(heroe: Heroe): Int
}

object Guerrero extends Trabajo(Stats(10, 15, 0, -10)) {
  def statPrincipal(heroe: Heroe): Int = heroe.getStats.fuerza
}

object Mago extends Trabajo(Stats(-20, 0, 0, 20)) {
  def statPrincipal(heroe: Heroe): Int = heroe.getStats.inteligencia
}

object Ladron extends Trabajo(Stats(-5, 0, 10, 0)) {
  def statPrincipal(heroe: Heroe): Int = heroe.getStats.velocidad
}