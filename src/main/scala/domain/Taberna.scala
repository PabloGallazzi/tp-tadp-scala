package domain
import util.control.Breaks._

/**
  * Created by Mariano on 17/6/2016.
  */
object Taberna {

  val tablonDeAnuncios: List[Mision] = List(misionBase,misionObtenerTalisman,misionConquistarDungeon)
  type Criterio = (Equipo,Equipo) => Boolean

  def elegirMisionSegunCriterio(equipo: Equipo,criterio: Criterio): Option[Mision] = {
     tablonDeAnuncios.filter(m=> m.sosRealizablePorEquipo(equipo)).sortWith((m1, m2) =>
        criterio(comoQuedariaEquipoAlHacerMision(equipo, m1),
          comoQuedariaEquipoAlHacerMision(equipo, m2))).headOption

  }

  private def comoQuedariaEquipoAlHacerMision(equipo: Equipo, mision:Mision): Equipo ={
    val copiaEquipo = equipo.getCopia
    mision.teVaARealizarEquipo(equipo) match{
      case equipoModificado:Equipo => {
        equipo.restaurarEstadoOriginal(copiaEquipo)
        equipoModificado
      }
      case _ => equipo
    }
  }


  def entrenarEquipo(equipo: Equipo) = {
    breakable {
      for (m <- tablonDeAnuncios) {
        if(m.sosRealizablePorEquipo(equipo))
        m.teVaARealizarEquipo(equipo)
        else break
      }
    }
  }


}
