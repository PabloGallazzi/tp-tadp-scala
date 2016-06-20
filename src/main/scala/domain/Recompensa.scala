package domain

/**
  * Created by pgallazzi on 20/6/16.
  */
abstract class Recompensa

class nuevoHeroeParaElEquipo(heroe: Heroe) extends Recompensa
class masOroParaElEquipo(oro: Int) extends Recompensa
class incrementarStats(stats: Stats, condicion: Heroe => Boolean) extends Recompensa
class unItemNuevoParaElEquipo(item: Item) extends Recompensa