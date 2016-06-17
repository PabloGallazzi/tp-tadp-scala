package domain

import scala.util.Random

/**
  * Created by Mariano on 14/6/2016.
  */
abstract class Tarea {

  def afectadorHeroe: (Heroe => Unit) = { x => }

  def afectadorEquipo: (Equipo => Unit) = { x => }

  def teVaARealizar(equipo: Equipo) = {
    if (this.sosRealizablePor(equipo)) {
      val masApto = equipo.masAptoParaTarea(this)
      afectadorHeroe(masApto)
      afectadorEquipo(equipo)
    }
  }

  def facilidadTarea(heroe: Heroe, equipo: Equipo): Double

  def sosRealizablePor(equipo: Equipo):Boolean = true

}

case object pelearContraMonstruo extends Tarea {
  override def afectadorHeroe: (Heroe => Unit) = {x => if(x.fuerza <20) x.hp =1}
  def facilidadTarea(heroe: Heroe, equipo: Equipo): Double = {
    equipo.getLider match {
      case h =>
        h.sosGuerrero match{
          case true => 20
          case false =>
            if (heroe.sosGuerrero) 10
            else 0
        }

    }
  }

}

case object forzarPuerta extends Tarea {
  def facilidadTarea(heroe: Heroe,equipo: Equipo): Double =
    10*equipo.integrantes.count(h=> h.sosLadron) + heroe.inteligencia


  override def afectadorHeroe: (Heroe => Unit) = {x=>
    if(!x.sosMago || !x.sosLadron) {
      x.hp -= 5
      x.inteligencia += 1
    }
  }

}

case object robarTalisman extends Tarea {
  var talismanes: Array[Talisman] = Array(talismanDeDedicacion,talismanMaldito,talismanDelMinimalismo)
  val rand = new scala.util.Random
  var random_index = rand.nextInt(talismanes.length)

  override def afectadorEquipo: (Equipo => Unit) = {x=> x.obtenerItem(talismanes(random_index)) }
  def facilidadTarea(heroe: Heroe,equipo: Equipo): Double = heroe.velocidad
  override def sosRealizablePor(equipo: Equipo) = equipo.getLider.sosLadron

}
