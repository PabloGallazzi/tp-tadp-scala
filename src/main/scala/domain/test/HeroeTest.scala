package domain.test

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Before
import domain._

/**
  * Created by Mariano on 11/6/2016.
  */
class HeroeTest {
  @Before
  val heroe: Heroe = new Heroe(1, 1, 1, 1)
  heroe.obtenerItem(palitoMagico)
  heroe.obtenerItem(talismanMaldito)
  heroe.obtenerItem(espadaDeLaVida)

  @Test
  def `los stats de un heroe sin trabjo se calculan correctamente` = {

    assert(heroe.getFuerza ==1 && heroe.getHP ==1 && heroe.getInteligencia ==1 && heroe.getVelocidad ==1)
  }

  @Test
  def `se agregan correctamente items al inventario y se equipan solo los que esten en el inventario` ={
    heroe.equipar(talismanMaldito)
    heroe.equipar(espadaDeLaVida)
    heroe.equipar(talismanDeDedicacion)
    println(heroe.getElementosEquipados)
    assertEquals(heroe.getElementosEquipados.size,2)
    assertEquals(heroe.getInventario.size, 3)
    println(heroe.getElementosEquipados)


  }

  @Test
  def `se quitan correctamete los items del equipamiento de un heroe` = {
    heroe.equipar(espadaDeLaVida)
    assertEquals(heroe.getElementosEquipados.size,1)
    heroe.desequipar(espadaDeLaVida)
    assertEquals(heroe.getElementosEquipados.size,0)
  }


  @Test
  def `se crea un guerrero y se calcula correctamente el stat principal` =
  {
    assertEquals(heroe.statPrincipal,0,0)
    heroe.sosGuerrero
    assertEquals(heroe.statPrincipal,16,0)
  }

  @Test
  def `se cambia el trabajo de un heroe correctamente y sus stat se modifican` = {
    heroe.sosGuerrero
    assertEquals(heroe.getHP, new Guerrero(heroe).incHP+ new Heroe(1, 1, 1, 1).hp,0)
    heroe.sosMago
    assertEquals(heroe.getHP, 1,0)
  }

  @Test
  def `un talisman maldito pone todos los stats en 1` = {
    heroe.sosGuerrero
    heroe.equipar(talismanMaldito)
    heroe.equipar(espadaDeLaVida)

    assertEquals(heroe.getFuerza, 1, 0)
  }

  @Test
  def `se agregan items al equipo del heroe y se calcula correctamente sus stats` = {
    heroe.sosGuerrero
    heroe.equipar(espadaDeLaVida)
    assertEquals(heroe.getFuerza,heroe.getHP,0)

  }

  @Test
  def `un heroe que no es mago no puede equipar un palitoMagico` = {
    heroe.equipar(palitoMagico)
    assertEquals(heroe.elementosEquipados.equipamiento.size,0,0)
    heroe.sosGuerrero
    heroe.equipar(palitoMagico)
    assertEquals(heroe.elementosEquipados.equipamiento.size,0,0)
    heroe.sosMago
    heroe.equipar(palitoMagico)
    assertEquals(heroe.elementosEquipados.equipamiento.size,1,0)

  }

  @Test
  def `palito magico altera la inteligencia en 20 a un mago` = {
    heroe.sosMago
    assertEquals(heroe.getInteligencia, 21, 0)
    heroe.equipar(palitoMagico)
    assertEquals(heroe.getInteligencia, 41, 0)
    assert(heroe.statPrincipal == heroe.getInteligencia)

  }
/*

  @Test
  def `cuando un heroe equipa un item que ocupa la misma parte del cuerpo, se reemplaza` = {
    heroe.obtenerItem(talismanDeDedicacion)
    heroe.equipar(talismanDeDedicacion)
    assert(heroe.getElementosEquipados.contains(talismanDeDedicacion))
    heroe.equipar(talismanMaldito)
    assert(!heroe.getElementosEquipados.contains(talismanDeDedicacion))
    assert(heroe.getElementosEquipados.contains(talismanMaldito))

  }
*/

}


