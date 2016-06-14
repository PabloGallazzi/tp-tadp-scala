package domain.test

import domain._
import org.junit.{Before, Test}
import org.junit.Assert.assertEquals

/**
  * Created by Mariano on 13/6/2016.
  */
class EquipoTest {
  @Before
  val heroe: Heroe = new Heroe(1, 1, 1, 1)
  val otroHeroe: Heroe = new Heroe(2, 2, 2, 2)
  val unEquipo: Equipo = new Equipo("un equipo")
  unEquipo.agregarMiembro(heroe)
  unEquipo.agregarMiembro(otroHeroe)
  heroe.obtenerItem(espadaDeLaVida)
  otroHeroe.obtenerItem(espadaDeLaVida)


  @Test
  def `se agregan integrantes correctamente a un equipo` = {
    assertEquals(unEquipo.integrantes.size, 2, 0)

  }

  @Test
  def `si un equipo tiene dos lideres devuelve null` = {
    assertEquals(unEquipo.getLider,null)
  }

  @Test
  def`si un equipo tiene lider lo devuelve con sus atributos stats e items` = {
    heroe.sosGuerrero()
    assertEquals(unEquipo.getLider,heroe)
    assertEquals(unEquipo.getLider.statPrincipal,16,0)
    heroe.obtenerItem(espadaDeLaVida)
    heroe.equipar(espadaDeLaVida)
    assertEquals(unEquipo.getLider.getElementosEquipados.size,1)
    assertEquals(unEquipo.getLider.getFuerza, unEquipo.getLider.getHP,0)

  }
  @Test
  def `cuando un equipo de un miembro obtiene un item, si lo puede equipar, se lo queda` ={
    unEquipo.quitarMiembro(otroHeroe)
    unEquipo.obtenerItem(talismanDeDedicacion)
    assertEquals(heroe.getInventario.size,1)
    assert(heroe.getInventario.contains(espadaDeLaVida))
  }

  @Test
  def `al ganar un item se lo queda el que mas aumenta su stat principal`= {
    heroe.sosGuerrero()
    otroHeroe.sosMago()
    assert(heroe.statPrincipal < otroHeroe.statPrincipal)
    unEquipo.obtenerItem(talismanDeDedicacion)
    assert(otroHeroe.getInventario.contains(talismanDeDedicacion))
  }

  @Test
  def `al ganar un item que no lo puede usar nadie, aumenta el oro en su valor` = {
    heroe.sosGuerrero()
    otroHeroe.sosLadron()
    unEquipo.obtenerItem(palitoMagico)
    assert(!otroHeroe.getInventario.contains(palitoMagico))
    assert(!heroe.getInventario.contains(palitoMagico))
    assertEquals(unEquipo.getOro, palitoMagico.getValor)

  }



/*
   @Test
   def `asd` = {
     heroe.sosGuerrero()
     heroe.equipar(espadaDeLaVida)
     val unHeroe: Heroe = heroe.getCopia
     unHeroe.obtenerItem(talismanDeDedicacion)
     unHeroe.equipar(talismanDeDedicacion)
     unHeroe.trabajo = heroe.getTrabajo
     assert(!unHeroe.eq(heroe))
     assert(!palitoMagico.sosEquipable(unHeroe))
    assertEquals((unHeroe.getFuerza, unHeroe.getInteligencia, unHeroe.getVelocidad, unHeroe.getElementosEquipados, unHeroe.getTrabajo, unHeroe.getInventario),
       (heroe.getFuerza, heroe.getInteligencia, heroe.getVelocidad, heroe.getElementosEquipados, heroe.getTrabajo, heroe.getInventario))

   }
*/
}
