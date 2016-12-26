

/**
  * Created by pgallazzi on 20/6/16.
  */
abstract class Recompensa {
  def darRecompensaAEquipo(equipo: Equipo): Equipo
}

class nuevoHeroeParaElEquipo(heroe: Heroe) extends Recompensa {
  override def darRecompensaAEquipo(equipo: Equipo): Equipo = {
    equipo.obtenerMiembro(heroe)
  }
}

class masOroParaElEquipo(oro: Int) extends Recompensa {
  override def darRecompensaAEquipo(equipo: Equipo): Equipo = {
    equipo.incrementarPozoComunEn(oro)
  }
}

class incrementarStats(stats: Stats, condicion: Heroe => Boolean) extends Recompensa {
  override def darRecompensaAEquipo(equipo: Equipo): Equipo = {
    var nuevoEquipo: Equipo = equipo
    def soloLosQueCumplenLaCondicion: List[Heroe] = equipo.integrantes.filter(condicion)
    def itemIncrementadorDeStats: Item = new Item({ statsViejos => new Stats(statsViejos.hp + stats.hp, statsViejos.fuerza + stats.fuerza, statsViejos.velocidad + stats.velocidad, statsViejos.inteligencia + stats.inteligencia) })
    soloLosQueCumplenLaCondicion.foreach(heroe => nuevoEquipo = nuevoEquipo.reemplazarMiembro(heroe, heroe.equiparUnItem(itemIncrementadorDeStats)))
    nuevoEquipo
  }
}

class unItemNuevoParaElEquipo(item: Item) extends Recompensa {
  override def darRecompensaAEquipo(equipo: Equipo): Equipo = {
    equipo.obtenerItem(item)
  }
}