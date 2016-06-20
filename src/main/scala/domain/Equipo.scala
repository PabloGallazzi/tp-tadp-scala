package domain


/**
  * Created by Mariano on 12/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Equipo(nombre: String,
                  integrantes: List[Heroe] = List(),
                  oro: Int = 0) {

  //TODO: Ver esto con Nico, no se si el concepto de None es el correcto
  def mejorHeroeSegun(criterio: Heroe => Int): Option[Heroe] = {
    def integrantesOrdenados: List[Heroe] = integrantes.sortBy(criterio)
    if (integrantesOrdenados.nonEmpty) {
      return Some(integrantesOrdenados.last)
    }
    None
  }

  def obtenerMiembro(heroe: Heroe): Equipo = {
    this.copy(integrantes = this.integrantes.++:(List(heroe)))
  }

  def reemplazarMiembro(heroeASacar: Heroe, heroeAPoner: Heroe): Equipo = {
    this.copy(integrantes = integrantes.filter(heroe => heroe != heroeASacar).++:(List(heroeAPoner)))
  }

  def obtenerItem(item: Item): Equipo = {
    def heroe: Option[Heroe] = obtenerMejorIntegranteParaItemONone(item)
    if (heroe.nonEmpty) {
      return reemplazarMiembro(heroe.get, heroe.get.equiparUnItem(item))
    }
    incrementarPozoComunEn(item.valor)
  }

  def incrementarPozoComunEn(cantidad: Int) = {
    copy(oro = this.oro + cantidad)
  }

  //TODO: Implementar esto!
  private def obtenerMejorIntegranteParaItemONone(item: Item): Option[Heroe] = {
    None
  }

  //TODO: Ver esto con Nico, no se si el concepto de None es el correcto
  def lider: Option[Heroe] = {
    def integrantesOrdenados: List[Heroe] = integrantes.sortBy({ heroe => heroe.getMainStatOrNone })
    if (integrantesOrdenados.nonEmpty) {
      return Some(integrantesOrdenados.last)
    }
    None
  }

}
