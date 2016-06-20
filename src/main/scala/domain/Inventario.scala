package domain

/**
  * Created by Mariano on 12/6/2016.
  */


class Inventario[T <: Item](var equipamiento:List[T] = List()) {


  //formato tupla: (incHP,incFz,incVel,incInt)
  val incrementadorDeEquipamiento: ((Double => Double),(Double => Double),(Double => Double),(Double => Double)) =
    ({x: Double => x}, {x: Double => x},{x: Double => x},{x: Double => x})


  def quitarItemDeLaClase(item: T) = {
    item match {
      case a:Casco => equipamiento = equipamiento.filter(x => !x.isInstanceOf[Casco])
      case b:Armadura => equipamiento = equipamiento.filter(x=> !x.isInstanceOf[Armadura])
      case c:Talisman => equipamiento = equipamiento.filter(x=> !x.isInstanceOf[Talisman])
      case d:Manipulable => equipamiento = d.quitarManipulables(equipamiento)
      }
  }


  def quitarItem(item:T) = equipamiento = equipamiento.filter(x=> x != item )

  def agregar(item: T) = {
    quitarItemDeLaClase(item)
    equipamiento = equipamiento.union(List(item))
  }


  def incrementadorStats(heroe: Heroe) : ((Double => Double),(Double => Double),(Double => Double),(Double => Double)) = {
    val listFunc: List[((Double => Double),(Double => Double),(Double => Double),(Double => Double))] =
    equipamiento.sortWith((i1,i2)=> i1.prioridad < i2.prioridad).
      map(e => (e.incrementarVida(heroe), e.incrementarFuerza(heroe),e.incrementarVelocidad(heroe), e.incrementarInteligenia(heroe)))
    listFunc.foldLeft(incrementadorDeEquipamiento)((a,b) => (a._1.andThen(b._1),a._2.andThen(b._2),a._3.andThen(b._3),a._4.andThen(b._4)))
  }

}

