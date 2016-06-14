package domain

/**
  * Created by Mariano on 12/6/2016.
  */


class Inventario[T <: Item] {

  var equipamiento:List[T] = List()
  val incrementadorDeEquipamiento: ((Double => Double),(Double => Double),(Double => Double),(Double => Double)) =
    ({x: Double => x}, {x: Double => x},{x: Double => x},{x: Double => x})

/*
  //Se puede hacer que funcione bien con una cadena de ifs (poco expresivo)
  def quitarItemDeLaClase(item: T) = {
    item.getClass match {
      case _: Class[Manipulable] => quitarManipulables(item)
      case _: Class[Casco] => equipamiento = equipamiento.filter(x => !x.isInstanceOf[Casco])
      case _: Class[Armadura] => equipamiento = equipamiento.filter(x=> !x.isInstanceOf[Armadura])
      case _: Class[Talisman] => equipamiento = equipamiento.filter(x=> !x.isInstanceOf[Talisman])
      }
  }
*/


  def quitarItem(item:T) = equipamiento = equipamiento.filter(x=> x != item )

  def agregar(item: T) = {
    //this.quitarItemDeLaClase(item)
    equipamiento = equipamiento.union(List(item))
  }

  def incrementadorStats(heroe: Heroe) : ((Double => Double),(Double => Double),(Double => Double),(Double => Double)) = {
    val listFunc: List[((Double => Double),(Double => Double),(Double => Double),(Double => Double))] =
    equipamiento.map(e => (e.incrementarVida(heroe), e.incrementarFuerza(heroe),e.incrementarVelocidad(heroe), e.incrementarInteligenia(heroe)))
    listFunc.foldLeft(incrementadorDeEquipamiento)((a,b) => (a._1.andThen(b._1),a._2.andThen(b._2),a._3.andThen(b._3),a._4.andThen(b._4)))
  }
/*


 // Problema de tipos en item para Manipulables. Se castea de manera provisoria (igual no compila)
  private def quitarManipulables[Manipulable](item: T): Unit = {
      val ListItemsManip: List[Manipulable] = equipamiento.filter(x =>  x.isInstanceOf[Manipulable]).asInstanceOf[List[Manipulable]]
      val itemManip: Manipulable = item.asInstanceOf[Manipulable]
      ListItemsManip.size match {
        case 2 => if (itemManip.getCantidadDeManos == 2) equipamiento = equipamiento.filter(x => !x.isInstanceOf[Manipulable])
            else equipamiento = equipamiento.filter(x => x != ListItemsManip.head)
        case 1 => if (itemManip.getCantidadDeManos == 2) equipamiento = equipamiento.filter(x => x != ListItemsManip.head)
      }


  }

*/


}

