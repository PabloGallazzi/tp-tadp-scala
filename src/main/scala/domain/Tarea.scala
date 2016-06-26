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
    
    /** Ver de hacer con for comprehension **/
    facilidadTarea(equipo).flatMap(f=> equipo.mejorHeroeSegun(f)).map(h=> equipo.reemplazarMiembro(h,afectadorDeHeroe(h)))


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