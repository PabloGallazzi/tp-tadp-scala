package domain.test

import domain._
import org.junit.{Before, Test}
import org.junit.Assert._

/**
  * Created by Mariano on 17/6/2016.
  */
class MisionTest {

  @Before
  val heroe: Heroe = new Heroe(1, 1, 1, 1)
  val otroHeroe: Heroe = new Heroe(2, 2, 2, 2)
  val unEquipo: Equipo = new Equipo("un equipo")
  unEquipo.agregarMiembro(heroe)
  unEquipo.agregarMiembro(otroHeroe)

  @Test
  def `un equipo donde no hay lider o el lider no es ladron no puede completar la mision quedando en robar talisman` ={

    assertEquals(misionBase.teVaARealizarEquipo(unEquipo),(robarTalisman,unEquipo))

    heroe.sosGuerrero
    assertEquals(misionBase.teVaARealizarEquipo(unEquipo),(robarTalisman,unEquipo))

  }

  @Test
  def `una mision realizada devuelve el estado del equipo y afecta a sus integrantes`= {
    otroHeroe.ahoraSosGuerrero()
    heroe.velocidad += 20
    heroe.ahoraSosLadron()
    assertEquals(misionBase.teVaARealizarEquipo(unEquipo),unEquipo)

    //luchar Con Monstruo Disminuye La Fuerza Del Mas Apto
    assertEquals(otroHeroe.getFuerza,17,0)

    //se Obtuvo El Talisman
    println(heroe.getInventario)
    println(otroHeroe.getInventario)
    assert(heroe.getInventario.count(x=> x.isInstanceOf[Talisman]) == 1 ||
      otroHeroe.getInventario.count(x=> x.isInstanceOf[Talisman]) == 1 )


  }


}
