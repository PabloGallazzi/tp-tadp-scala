package domain

import scala.util.Random

/**
  * Created by Mariano on 14/6/2016.
  */
abstract class Tarea {

  def afectadorHeroe: (Heroe => Unit) = { x => }

  def facilidadTarea(equipo: Equipo): Option[Heroe => Double]

  def teVaARealizar(equipo: Equipo): Resultado = {
    val masApto = equipo.masAptoParaTarea(this)
    masApto match {
      case Some(h) => {
        afectadorHeroe(h)
        new Exito(equipo)
      }
      case None => new Fracaso(this,equipo)
    }
  }

}


case object pelearContraMonstruo extends Tarea {

  override def afectadorHeroe: (Heroe => Unit) = {x => if(x.fuerza < 20) x.hp = 1}

  def facilidadTarea(equipo: Equipo): Option[Heroe => Double] = {
      Some({ x =>
      equipo.getLider match {
        case Some(h) =>
          h.sosGuerrero match {
            case true => 20
            case false =>
              if (x.sosGuerrero) 10
              else 0
          }
        case None => 0
      }
    })
  }

}

case object forzarPuerta extends Tarea {

  def facilidadTarea(equipo: Equipo): Option[Heroe => Double] = {
    Some({x=>(10 * equipo.integrantes.count(h => h.sosLadron)) + x.getInteligencia})
  }


  override def afectadorHeroe: (Heroe => Unit) = {x=>
    if(!(x.sosMago || x.sosLadron)) {
      x.hp -= 5
      x.inteligencia += 1
    }
  }

}

case object robarTalisman extends Tarea {
  var talismanes: Array[Talisman] = Array(talismanDeDedicacion,talismanMaldito,talismanDelMinimalismo)
  val rand = new scala.util.Random
  var random_index = rand.nextInt(talismanes.length)

  override def afectadorHeroe: (Heroe => Unit) = {x=> x.obtenerItem(talismanes(random_index)) }

  def facilidadTarea(equipo: Equipo): Option[Heroe => Double] = {
    if (equipo.getLider.exists(h=>h.sosLadron)) Some({h => h.velocidad})
    else None

  }



}
