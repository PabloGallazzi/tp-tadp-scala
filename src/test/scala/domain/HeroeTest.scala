package domain

import org.junit.{Before, Test}

/**
  * Created by Mariano on 11/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
class HeroeTest {
  @Before
  val stats: Stats = new Stats(10, 5, 6, 9)
  val heroe: Heroe = new Heroe(stats, Some(Guerrero))
  val itemSaraza: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => var puede: Boolean = true
    puede = puede && (heroe.trabajo match {
      case Some(Guerrero) => true
      case _ => false
    })
    puede = puede && heroe.getStats.fuerza >= 5
    puede
  }, Some(Cabeza))

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
    val itemSinPosicion: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => var puede: Boolean = false
      puede = heroe.trabajo match {
        case Some(Guerrero) => true
        case _ => false
      }
      puede = puede && heroe.getStats.fuerza >= 5
      puede
    }, None)
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
    val itemSinPosicion: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => var puede: Boolean = true
      puede = puede && heroe.getStats.fuerza >= 5
      puede
    }, None)
    var nuevoHeroe: Heroe = heroe.cambiarDeTrabajo(None)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSinPosicion)
    assert(nuevoHeroe.getStats.hp == 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.baseStats == stats)
  }

}


