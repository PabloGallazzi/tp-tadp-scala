package domain

/**
  * Created by Mariano on 14/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
case class Tarea(validadorEquipoPuedeRealizar: Equipo => Boolean,
                 calculadorDeFacilidad: Heroe => Int,
                 afectadorDeHeroe: Heroe => Heroe) {

  private def facilidadTarea(equipo: Equipo): Option[Heroe => Int] = {
    if (validadorEquipoPuedeRealizar(equipo)) {
      return Some(calculadorDeFacilidad)
    }
    None
  }


  def realizarPorEquipo(equipo: Equipo): Resultado = {
    (for {x1 <- facilidadTarea(equipo)
          x2 <- equipo.mejorHeroeSegun(x1)
    } yield equipo.reemplazarMiembro(x2, afectadorDeHeroe(x2))).fold[Resultado](Fracaso(this, equipo))(equipo => Exito(equipo))
  }

}