package domain


/**
  * Created by Mariano on 10/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Heroe(baseStats: Stats,
                 trabajo: Option[Trabajo] = None,
                 itemsEquipado: List[Item] = List[Item](),
                 inventario: List[Item] = List[Item]()) {

  def equiparUnItem(item: Item): Heroe = {
    if (item.funcionRestriccionParaPortar(this)) {
      var filteredList: List[Item] = this.itemsEquipado
      if (item.parteDelCuerpoQueOcupa.isDefined) {
        filteredList = this.itemsEquipado.filter(itemFromList => itemFromList.parteDelCuerpoQueOcupa != item.parteDelCuerpoQueOcupa)
      }
      return this.copy(itemsEquipado = filteredList.++:(List(item)))
    }
    this
  }

  def agregarUnItemAlInventario(item: Item): Heroe = {
    this.copy(inventario = this.inventario.++:(List(item)))
  }

  def getStats: Stats = {
    val newStats: Stats = itemsEquipado.foldRight[Stats](afterWorkStats)((item: Item, stats: Stats) => item.funcionModificadora(stats))
    new Stats(newStats.hp.max(1), newStats.fuerza.max(1), newStats.velocidad.max(1), newStats.inteligencia.max(1))
  }

  def cambiarDeTrabajo(unTrabajo: Option[Trabajo]): Heroe = {
    this.copy(trabajo = unTrabajo)
  }

  def getMainStatOrNone: Option[Int] = {
    trabajo match {
      case Some(_) => Some(trabajo.get.statPrincipal(this))
      case None => None
    }
  }

  private def afterWorkStats: Stats = {
    trabajo match {
      case Some(_) => new Stats((baseStats.hp + trabajo.get.stats.hp).max(1), (baseStats.fuerza + trabajo.get.stats.fuerza).max(1), (baseStats.velocidad + trabajo.get.stats.velocidad).max(1), (baseStats.inteligencia + trabajo.get.stats.inteligencia).max(1))
      case None => baseStats
    }
  }

}