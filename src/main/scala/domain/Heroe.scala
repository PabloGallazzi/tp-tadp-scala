package domain

/**
  * Created by Mariano on 10/6/2016.
  */
case class Heroe(var hp: Double, var fuerza:Double,
                 var velocidad:Double,
                 var inteligencia: Double,
                 var trabajo: Option[TipoTrabajo] = None){

  var inventario: Set[Item] = Set()
  val elementosEquipados: Inventario[Item] = new Inventario[Item]
  var copia: Option[Heroe] = None


  def ahoraSosGuerrero() = trabajo = Some(Guerrero)
  def ahoraSosMago() = trabajo = Some(Mago)
  def ahoraSosLadron() = trabajo = Some(Ladron)
  def ahoraNoTenesTrabajo() =  trabajo = None

  def sosMago = trabajo.orNull.eq(Mago)
  def sosGuerrero = trabajo.orNull.eq(Guerrero)
  def sosLadron = trabajo.orNull.eq(Ladron)
  def tenesTrabajo = !trabajo.isEmpty

  def statPrincipal: Double =
   trabajo.map(t => t.statPrincipal(this)).getOrElse(0.0)



  def equipar(item: Item) =
    if (inventario.contains(item) && item.sosEquipable(this)) elementosEquipados.agregar(item)

  def desequipar(item:Item) = elementosEquipados.quitarItem(item)

  def getHP: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this).
      _1.apply(getHPTrabajo))

  def getFuerza: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this).
      _2.apply(getFuerzaTrabajo))

  def getVelocidad: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this).
      _3.apply(getVelocidadTrabajo))

  def getInteligencia: Double =
    this.statMinimo(elementosEquipados.incrementadorStats(this).
      _4.apply(getInteligenciaTrabajo))

  def getElementosEquipados=
    elementosEquipados.equipamiento
  def setElementosEquipados(elems: List[Item]) =
    elementosEquipados.equipamiento = elems


  def getInventario = inventario
  def setInventario(inv: Set[Item]) = inventario = inv

  def obtenerItem(item: Item) = inventario += item

  def getCopia = copy(hp,fuerza,velocidad,inteligencia,trabajo)


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

  /**MISION**/
  def hacerCopiaConEquipamiento() = {
    val heroeCopia: Heroe = getCopia
    heroeCopia.setInventario(inventario)
    heroeCopia.setElementosEquipados(getElementosEquipados)
    copia = Some(heroeCopia)
  }

  def restaurarEstadoOriginal() =
    if (copia.isDefined){
        hp = copia.get.hp
        fuerza = copia.get.fuerza
        inteligencia = copia.get.inteligencia
        velocidad = copia.get.velocidad
        inventario =   copia.get.getInventario
        setElementosEquipados(copia.get.getElementosEquipados)
      }



}


