package domain

import org.junit._

/**
  * Created by Mariano on 11/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
class HeroeTest extends BaseTest{

  @Test
  def `test_el_heroe_se_crea_correctamente`() = {
    assert(heroe.baseStats.hp == 10 && heroe.baseStats.fuerza == 5 && heroe.baseStats.velocidad == 6 && heroe.baseStats.inteligencia == 9 && heroe.trabajo.contains(Guerrero) && heroe.itemsEquipado.isEmpty)
  }

  @Test
  def `test_el_heroe_puede_equiparse_un_item`() = {
    val nuevoHeroe: Heroe = heroe.equiparUnItem(itemSaraza)
    assert(nuevoHeroe.itemsEquipado.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipado.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_agregar_item_al_inventario_no_afecta_stats`() = {
    val nuevoHeroe: Heroe = heroe.agregarUnItemAlInventario(itemSaraza)
    assert(nuevoHeroe.inventario.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.inventario.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_se_equipa_con_algo_que_ya_tiene_ocupado_y_queda_bien`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemSaraza)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSaraza)
    assert(nuevoHeroe.itemsEquipado.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipado.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_se_equipa_con_que_no_tiene_posicion`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemSaraza)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSinPosicion)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSinPosicion)
    assert(nuevoHeroe.itemsEquipado.size == 3)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10 + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipado.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_puede_cambiar_de_trabajo`() = {
    val nuevoHeroe: Heroe = heroe.cambiarDeTrabajo(Some(Mago))
    assert(nuevoHeroe.getStats.hp == 1)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_puede_cambiar_de_trabajo_por_none_y_funciona`() = {
    val nuevoHeroe: Heroe = heroe.cambiarDeTrabajo(None)
    assert(nuevoHeroe.getStats.hp == 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.baseStats == stats)
  }

}


