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
  heroe.obtenerItem(talismanDelMinimalismo)

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
    assertEquals(heroe.getInventario.size, 4)
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
    heroe.ahoraSosGuerrero
    assertEquals(heroe.statPrincipal,16,0)
  }

  @Test
  def `se cambia el trabajo de un heroe correctamente y sus stat se modifican` = {
    heroe.ahoraSosGuerrero
    assertEquals(heroe.getHP, Guerrero.incHP + new Heroe(1, 1, 1, 1).hp,0)
    heroe.ahoraSosMago
    assertEquals(heroe.getHP, 1,0)
  }

  @Test
  def `un talisman maldito pone todos los stats en 1` = {
    heroe.ahoraSosGuerrero
    heroe.equipar(talismanMaldito)
    heroe.equipar(espadaDeLaVida)

    assertEquals(heroe.getFuerza, 1, 0)
  }

  @Test
  def `se agregan items al equipo del heroe y se calcula correctamente sus stats` = {
    heroe.ahoraSosGuerrero
    heroe.equipar(espadaDeLaVida)
    assertEquals(heroe.getFuerza,heroe.getHP,0)

  }

  @Test
  def `un heroe que no es mago no puede equipar un palitoMagico` = {
    heroe.equipar(palitoMagico)
    assertEquals(heroe.elementosEquipados.equipamiento.size,0,0)
    heroe.ahoraSosGuerrero
    heroe.equipar(palitoMagico)
    assertEquals(heroe.elementosEquipados.equipamiento.size,0,0)
    heroe.ahoraSosMago
    heroe.equipar(palitoMagico)
    assertEquals(heroe.elementosEquipados.equipamiento.size,1,0)

  }

  @Test
  def `palito magico altera la inteligencia en 20 a un mago` = {
    heroe.ahoraSosMago
    assertEquals(heroe.getInteligencia, 21, 0)
    heroe.equipar(palitoMagico)
    assertEquals(heroe.getInteligencia, 41, 0)
    assert(heroe.statPrincipal == heroe.getInteligencia)

  }

  @Test
  def `cuando un heroe equipa un item que ocupa una parte del cuerpo ya ocupada por otro item se reemplaza` = {

    heroe.obtenerItem(talismanDeDedicacion)
    heroe.equipar(talismanDeDedicacion)
    assert(heroe.getElementosEquipados.contains(talismanDeDedicacion))
    heroe.equipar(talismanMaldito)
    assert(!heroe.getElementosEquipados.contains(talismanDeDedicacion))
    assert(heroe.getElementosEquipados.contains(talismanMaldito))
  }

  @Test
  def `solo un horeo sin trabajo puede equipar la vincha Del Bufalo De Agua y modifica los stats segun su condicion` = {
    heroe.ahoraSosLadron()
    heroe.obtenerItem(vinchaDelBufaloDeAgua)
    heroe.equipar(vinchaDelBufaloDeAgua)
    assert(!heroe.getElementosEquipados.contains(vinchaDelBufaloDeAgua))

    heroe.ahoraNoTenesTrabajo()
    heroe.equipar(vinchaDelBufaloDeAgua)
    assertEquals((heroe.getFuerza,heroe.getHP,heroe.getInteligencia,heroe.getVelocidad),(11.0,11.0,1.0,11.0))

    val otroHeroe: Heroe = new Heroe(1,2,1,1)
    otroHeroe.obtenerItem(vinchaDelBufaloDeAgua)
    otroHeroe.equipar(vinchaDelBufaloDeAgua)
    assertEquals((otroHeroe.getFuerza,otroHeroe.getHP,otroHeroe.getInteligencia,otroHeroe.getVelocidad),(2.0,1.0,31.0,1.0))

  }

  @Test
  def `talismanDeLaDedicacion incrementa todos los stats en el 10% del stat princpal` ={
    heroe.obtenerItem(talismanDeDedicacion)
    heroe.equipar(talismanDeDedicacion)
    assertEquals(heroe.getFuerza, 1, 0)
    heroe.ahoraSosMago()
    assertEquals(heroe.getFuerza,3.1,0)
  }


  @Test
  def `los items afectan al heroe segun su prioridad` = {
    heroe.sosMago
    heroe.equipar(espadaDeLaVida)
    heroe.equipar(talismanDelMinimalismo)
    assertEquals(heroe.getHP,heroe.getFuerza,0)
    heroe.equipar(talismanMaldito)
    assert(heroe.getFuerza ==1 && heroe.getHP ==1 && heroe.getInteligencia ==1 && heroe.getVelocidad ==1)
  }

}


