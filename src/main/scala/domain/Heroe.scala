package domain

/**
  * Created by Mariano on 10/6/2016.
  */
case class Heroe(var hp: Double, var fuerza:Double, var velocidad:Double, var inteligencia: Double){

  var trabajo: Option[TipoTrabajo] = None
  var inventario: Set[Item] = Set()
  val elementosEquipados: Inventario[Item] = new Inventario[Item]
  var necesitasCurarte: Boolean = false


  def sosGuerrero() = trabajo = Some(new Guerrero(this))

  def sosMago() = trabajo = Some(new Mago(this))
  def sosLadron() = trabajo = Some(new Ladron(this))
  def noTenesTrabajo() =  trabajo = None

  def statPrincipal = {
    trabajo match{
        case Some(x) => x.statPrincipal
        case None => 0.0
    }
  }

  def ralizarTarea(tarea: Tarea) = tarea.tePudoRealizar(this)

  def equipar(item: Item) =
    if (inventario.contains(item) && item.sosEquipable(this)) elementosEquipados.agregar(item)

  def desequipar(item:Item) = elementosEquipados.quitarItem(item)

  def getHP: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this)._1.apply(getHPTrabajo))

  def getFuerza: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this)._2.apply(getFuerzaTrabajo))

  def getVelocidad: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this)._3.apply(getVelocidadTrabajo))

  def getInteligencia: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this)._4.apply(getInteligenciaTrabajo))

  def getElementosEquipados=
    elementosEquipados.equipamiento

  def getTrabajo =
    trabajo

  def getInventario =
    inventario

  def obtenerItem(item: Item) = inventario += item

  def teMataron() = necesitasCurarte = true
  def estasCurado() = necesitasCurarte = false

  def getCopia = copy(hp,fuerza,velocidad,inteligencia)
  private def getFuerzaTrabajo: Double = {
    trabajo.map(x => x.incFz).getOrElse(0.0)+ fuerza
  }
  private def getVelocidadTrabajo: Double = {
    trabajo.map(x => x.incVel).getOrElse(0.0)+velocidad
  }
  private def getInteligenciaTrabajo: Double = {
    trabajo.map(x => x.incInt).getOrElse(0.0)+inteligencia
  }
  private def getHPTrabajo: Double= {
    trabajo.map(x => x.incHP).getOrElse(0.0)+hp
  }

  private def statMinimo(stat:Double): Double ={
    if (stat>1) stat
    else 1
  }

}


