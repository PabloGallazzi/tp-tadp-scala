package domain

import scala.util.Try


/**
  * Created by Mariano on 12/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Equipo(nombre: String,
                  integrantes: List[Heroe] = List(),
                  oro: Int = 0) {

  def mejorHeroeSegun(criterio: Heroe => Int): Option[Heroe] = {
    integrantes.sortBy(criterio).lastOption
  }

  def obtenerMiembro(heroe: Heroe): Equipo = {
    this.copy(integrantes = integrantes.++:(List(heroe)))
  }

  def reemplazarMiembro(heroeASacar: Heroe, heroeAPoner: Heroe): Equipo = {
    this.copy(integrantes = integrantes.filter(heroe => heroe != heroeASacar).++:(List(heroeAPoner)))
  }

  def obtenerItem(item: Item): Equipo = {
    def heroe: Option[Heroe] = obtenerMejorIntegranteParaItemONone(item)
    heroe.map(h => reemplazarMiembro(h, h.equiparUnItem(item))).getOrElse(incrementarPozoComunEn(item.valor))
  }

  def incrementarPozoComunEn(cantidad: Int): Equipo = {
    copy(oro = this.oro + cantidad)
  }

  private def obtenerMejorIntegranteParaItemONone(item: Item): Option[Heroe] = {
    def mapped: List[(Int, Heroe)] = integrantes.map(heroe => {
      def heroeModificado = heroe.equiparUnItem(item)
      (heroeModificado.getMainStatOrNone.getOrElse(0) - heroe.getMainStatOrNone.getOrElse(0), heroe)
    }).filter(x => x._1 != 0)
    Try(mapped).map(_.maxBy(it => it._1)).map(_._2).toOption
  }

  def lider: Option[Heroe] = {
    integrantes.sortBy({ heroe => heroe.getMainStatOrNone }).lastOption
  }

}
