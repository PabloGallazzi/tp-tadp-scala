package domain

/**
  * Created by Mariano on 12/6/2016.
  */
case class Equipo(val nombre: String) {

  var integrantes: List[Heroe] = List()
  var oro:Int = 0


  def getOro = oro

  def agregarMiembro(heroe: Heroe) = integrantes = integrantes union List(heroe)

  def reemplazarMiembro(unHeroe: Heroe, otroHeroe: Heroe) = {
    quitarMiembro(unHeroe)
    agregarMiembro(otroHeroe)
  }


  def quitarMiembro(heroe: Heroe) = integrantes = integrantes.filter(x => x.eq(heroe))

  def obtenerItem(item: Item) = {
    val heroesQuePuedenEquiparItem: List[Heroe] = integrantes.filter(x=> item.sosEquipable(x))
    if(heroesQuePuedenEquiparItem.isEmpty) venderItem(item)
    else
    asignarItem(item, heroesQuePuedenEquiparItem, heroesQuePuedenEquiparItem.head)

  }

  def venderItem(item: Item) = oro += item.getValor

  def realizarTarea(tarea: Tarea) = sePudoHacerTarea(integrantes,tarea)

  private def sePudoHacerTarea(heroes: List[Heroe],tarea:Tarea ): Boolean = {
    integrantes match {
      case x :: xs => {
        if (x.ralizarTarea(tarea)) true
        else sePudoHacerTarea(xs , tarea)
      }
      case Nil => false

    }
  }


  private def asignarItem(item: Item, integrantes: List[Heroe], heroe: Heroe): Unit = {

    if (integrantes.isEmpty && item.sosEquipable(heroe)) heroe.obtenerItem(item)
    else {
      val unHeroe: Heroe = integrantes.head.getCopia
      val otroHeroe: Heroe = heroe.getCopia
      unHeroe.trabajo = integrantes.head.getTrabajo
      unHeroe.obtenerItem(item)
      unHeroe.equipar(item)
      otroHeroe.trabajo = heroe.getTrabajo
      otroHeroe.obtenerItem(item)
      otroHeroe.equipar(item)
      if (unHeroe.statPrincipal > otroHeroe.statPrincipal) asignarItem(item, integrantes.tail, integrantes.head)
      else asignarItem(item, integrantes.tail, heroe)
    }
  }


  def getLider: Heroe = {
    val statsPrincipales: List[Double] = integrantes.map(h => h.statPrincipal)
    val maximo: Double = statsPrincipales.max
    if (statsPrincipales.count(x => x == maximo) == 2) null
    else {
      integrantes.filter(x=> x.statPrincipal == maximo).head
    }
  }
}
