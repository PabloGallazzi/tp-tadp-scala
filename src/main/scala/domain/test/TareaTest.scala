package domain.test

import domain._
import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * Created by Mariano on 17/6/2016.
  */

class TareaTest {

  @Before
  val heroe: Heroe = new Heroe(1, 1, 1, 1)
  val otroHeroe: Heroe = new Heroe(2, 2, 2, 2)
  val unEquipo: Equipo = new Equipo("un equipo")
  unEquipo.agregarMiembro(heroe)
  unEquipo.agregarMiembro(otroHeroe)

  @Test
  def `un equipo sin lider o que su lider no es ladron no puede hacer la tarea robarTalisman` ={

    assert(robarTalisman.facilidadTarea(unEquipo).isEmpty)
    heroe.ahoraSosMago()
    assert(robarTalisman.facilidadTarea(unEquipo).isEmpty)


  }

  @Test
  def `un equipo que su lider es ladron termina la tarea robarTalisman y obtiene un talisman random` ={
    heroe.ahoraSosLadron()
    assert(robarTalisman.facilidadTarea(unEquipo).isDefined)
    robarTalisman.teVaARealizar(unEquipo)
    println(heroe.inventario)
    println(otroHeroe.inventario)
  }

  @Test
  def `un equipo realiza la tarea forzar puerta y el heroe que la realiza se ve afectado segun su trabajo` = {

    assert(forzarPuerta.facilidadTarea(unEquipo).get(heroe) <
      forzarPuerta.facilidadTarea(unEquipo).get(otroHeroe))
    forzarPuerta.teVaARealizar(unEquipo)
    assertEquals((otroHeroe.hp,otroHeroe.inteligencia),(-3,3))

    heroe.ahoraSosLadron()
    otroHeroe.ahoraSosMago()
    assert(forzarPuerta.facilidadTarea(unEquipo).get(heroe) <
      forzarPuerta.facilidadTarea(unEquipo).get(otroHeroe))
    assertEquals(forzarPuerta.facilidadTarea(unEquipo).get(otroHeroe),10+otroHeroe.getInteligencia,0)
    forzarPuerta.teVaARealizar(unEquipo)
    assertEquals((otroHeroe.hp,otroHeroe.inteligencia),(-3,3))

  }

  @Test
  def `un equipo realiza la tarea pelearConMonstruo y la facilidad depende del trabajo del lider y del heroe` ={
    //Sin Lider
    assert(pelearContraMonstruo.facilidadTarea(unEquipo).get(heroe) ==
      pelearContraMonstruo.facilidadTarea(unEquipo).get(otroHeroe))
    unEquipo.quitarMiembro(heroe)
    pelearContraMonstruo.teVaARealizar(unEquipo)
    assertEquals(otroHeroe.getHP,1,0)

    unEquipo.agregarMiembro(heroe)

    //Lider guerrero
    otroHeroe.ahoraSosGuerrero()
    assert(pelearContraMonstruo.facilidadTarea(unEquipo).get(heroe) ==
      pelearContraMonstruo.facilidadTarea(unEquipo).get(otroHeroe))
    assertEquals(pelearContraMonstruo.facilidadTarea(unEquipo).get(otroHeroe),20,0)
    pelearContraMonstruo.teVaARealizar(unEquipo)
    assertEquals(otroHeroe.getHP,11,0)

    //Lider no guerrero y heroe que realiza la tarea guerrero
    heroe.ahoraSosMago()
    assert(pelearContraMonstruo.facilidadTarea(unEquipo).get(heroe) <
      pelearContraMonstruo.facilidadTarea(unEquipo).get(otroHeroe))
    assertEquals(pelearContraMonstruo.facilidadTarea(unEquipo).get(otroHeroe),10,0)
    pelearContraMonstruo.teVaARealizar(unEquipo)
    assertEquals(otroHeroe.getHP,11,0)

    //Sin guerreros
    otroHeroe.ahoraSosLadron()
    assertEquals(pelearContraMonstruo.facilidadTarea(unEquipo).get(otroHeroe),0,0)
    pelearContraMonstruo.teVaARealizar(unEquipo)
    assertEquals(otroHeroe.getHP,1,0)

  }



}

