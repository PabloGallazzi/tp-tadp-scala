package domain


/**
  * Created by Mariano on 12/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Equipo(nombre: String,
                  integrantes: List[Heroe] = List(),
                  oro: Int = 0) {

  def mejorHeroeSegun(criterio: Heroe => Int): Option[Heroe] = {
    def integrantesOrdenados: List[Heroe] = integrantes.sortBy(criterio)
    integrantesOrdenados match {
      case Nil => None
      case _ => Some(integrantesOrdenados.last)
    }
  }

  def obtenerMiembro(heroe: Heroe): Equipo = {
    this.copy(integrantes = this.integrantes.++:(List(heroe)))
  }

  def reemplazarMiembro(heroeASacar: Heroe, heroeAPoner: Heroe): Equipo = {
    this.copy(integrantes = integrantes.filter(heroe => heroe != heroeASacar).++:(List(heroeAPoner)))
  }

  def obtenerItem(item: Item): Equipo = {
    def heroe: Option[Heroe] = obtenerMejorIntegranteParaItemONone(item)
    heroe match {
      case None => incrementarPozoComunEn(item.valor)
      case _ => reemplazarMiembro(heroe.get, heroe.get.equiparUnItem(item))
    }
  }

  def incrementarPozoComunEn(cantidad: Int) = {
    copy(oro = this.oro + cantidad)
  }

  private def obtenerMejorIntegranteParaItemONone(item: Item): Option[Heroe] = {
    def mapped: List[(Int, Heroe)] = integrantes.map(heroe => {
      def heroeModificado = heroe.equiparUnItem(item)
      heroeModificado.trabajo match {
        case Some(_) => {
          heroe.trabajo match {
            case Some(_) => (heroeModificado.trabajo.get.statPrincipal(heroeModificado) - heroe.trabajo.get.statPrincipal(heroe), heroe)
            case None => null
          }
        }
        case None => null
      }
    })
    mapped match {
      case Nil => None
      case something => Some(something.sortBy(it => it._1).last._2)
    }
  }

  def lider: Option[Heroe] = {
    def integrantesOrdenados: List[Heroe] = integrantes.sortBy({ heroe => heroe.getMainStatOrNone })
    integrantesOrdenados match {
      case Nil => None
      case _ => Some(integrantesOrdenados.last)
    }
  }

}
