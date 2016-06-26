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


  def realizarPorEquipo(equipo: Equipo): Option[Equipo] = {

    //Opcion con funciones de orden superior
    //facilidadTarea(equipo).flatMap(f=> equipo.mejorHeroeSegun(f)).map(h=> equipo.reemplazarMiembro(h,afectadorDeHeroe(h)))

    //Opcion con for comprehension
    for{ x1 <- facilidadTarea(equipo)
      x2 <- equipo.mejorHeroeSegun(x1)
    } yield equipo.reemplazarMiembro(x2,afectadorDeHeroe(x2))

    //Opcion con pattern matching
 /*   def criterio: Option[Heroe => Int] = facilidadTarea(equipo)
    criterio match {
      case None => None
      case Some(_) => {
        def heroeONone: Option[Heroe] = equipo.mejorHeroeSegun(criterio.get)
        heroeONone match {
          case None => None
          case Some(_) => Some(equipo.reemplazarMiembro(heroeONone.get, afectadorDeHeroe(heroeONone.get)))
        }
      }
    }*/

  }

}