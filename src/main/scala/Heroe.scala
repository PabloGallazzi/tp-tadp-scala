

/**
  * Created by Mariano on 10/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Heroe(baseStats: Stats,
                 trabajo: Option[Trabajo] = None,
                 itemsEquipados: List[Item] = List[Item]()) {

  def equiparUnItem(item: Item): Heroe = {
    if (item.funcionRestriccionParaPortar(this)) {
      val filteredList: List[Item] = item.parteDelCuerpoQueOcupa.fold(itemsEquipados) { parteDelCuerpo => itemsEquipados.filterNot(item => item.parteDelCuerpoQueOcupa.isEmpty || Cuerpo.conflictuan(parteDelCuerpo, item.parteDelCuerpoQueOcupa.get)) }
      return this.copy(itemsEquipados = filteredList :+ item)
    }
    this
  }

  def getStats: Stats = {
    val newStats: Stats = itemsEquipados.foldRight[Stats](afterWorkStats)((item: Item, stats: Stats) => item.funcionModificadora(stats))
    new Stats(newStats.hp.max(1), newStats.fuerza.max(1), newStats.velocidad.max(1), newStats.inteligencia.max(1))
  }

  def cambiarDeTrabajo(unTrabajo: Option[Trabajo]): Heroe = {
    this.copy(trabajo = unTrabajo)
  }

  def getMainStatOrNone: Option[Int] = trabajo.map(_.statPrincipal(this))


  private def afterWorkStats: Stats = trabajo.fold(baseStats) {
    trabajo => new Stats((baseStats.hp + trabajo.stats.hp).max(1), (baseStats.fuerza + trabajo.stats.fuerza).max(1), (baseStats.velocidad + trabajo.stats.velocidad).max(1), (baseStats.inteligencia + trabajo.stats.inteligencia).max(1))
  }

}