package domain


/**
  * Created by Mariano on 12/6/2016.
  */
case class Equipo(nombre: String, var integrantes: List[Heroe] = List(), var oro:Int = 0) {


  def agregarMiembro(heroe: Heroe) =
    integrantes = integrantes union List(heroe)


  def reemplazarMiembro(unHeroe: Heroe, otroHeroe: Heroe) = {
    quitarMiembro(unHeroe)
    agregarMiembro(otroHeroe)
  }

  def quitarMiembro(heroe: Heroe) = integrantes = integrantes.filter(x => !x.eq(heroe))

  def obtenerItem(item: Item) = {
    val heroesQuePuedenEquiparItem: List[Heroe] = integrantes.filter(x=> item.sosEquipable(x))
    if(heroesQuePuedenEquiparItem.isEmpty) venderItem(item)
    else
    asignarItem(item, heroesQuePuedenEquiparItem, heroesQuePuedenEquiparItem.head)
  }

  def venderItem(item: Item) = oro += item.getValor


  private def asignarItem(item: Item, integrantes: List[Heroe], heroe: Heroe): Unit = {

    if (integrantes.isEmpty && item.sosEquipable(heroe)) heroe.obtenerItem(item)
    else {
      val unHeroe: Heroe = integrantes.head.getCopia
      val otroHeroe: Heroe = heroe.getCopia
      unHeroe.obtenerItem(item)
      unHeroe.equipar(item)
      otroHeroe.obtenerItem(item)
      otroHeroe.equipar(item)
      if (unHeroe.statPrincipal > otroHeroe.statPrincipal) asignarItem(item, integrantes.tail, integrantes.head)
      else asignarItem(item, integrantes.tail, heroe)
    }
  }

  def getLider: Option[Heroe] = {
    val statsPrincipales: List[Double] = integrantes.map(h => h.statPrincipal)
    val maximo: Double = statsPrincipales.max
    if (statsPrincipales.count(x => x == maximo) == 2) None
    else
      integrantes.filter(x=> x.statPrincipal == maximo).headOption

  }

  /** TAREA **/
  def masAptoParaTarea(tarea: Tarea): Heroe = {
    integrantes.sortWith((h1, h2) => tarea.facilidadTarea(h1, this) > tarea.facilidadTarea(h2, this)).head
  }


  /** MISION **/
  def copiarIntegrantes = {
    integrantes.foreach(h => h.hacerCopiaConEquipamiento())
  }

  def getCopia ={
    this.copiarIntegrantes
    copy(nombre,integrantes,oro)
  }

  def realizarMision(mision: Mision) = mision.teVaARealizarEquipo(this)

  def restaurarEstadoOriginal(equipo:Equipo) = {
    oro = equipo.oro
    integrantes.foreach(h => h.restaurarEstadoOriginal())
  }


}
