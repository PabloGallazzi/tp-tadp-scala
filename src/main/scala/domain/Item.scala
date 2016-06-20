package domain

/**
  * Created by Mariano on 11/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Item(funcionModificadora: Stats => Stats = { stats => stats },
                funcionRestriccionParaPortar: Heroe => Boolean = { heroe => true },
                parteDelCuerpoQueOcupa: Option[Cuerpo] = None,
                valor: Int = 0) {
}