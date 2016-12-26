import org.junit.Test

/**
  * Created by Mariano on 11/6/2016.
  * Modified by PabloGallazzi on 20/6/2016.
  */
class HeroeSpec extends BaseSpec{

  @Test
  def `test_el_heroe_se_crea_correctamente`() = {
    assert(heroe.baseStats.hp == 10 && heroe.baseStats.fuerza == 5 && heroe.baseStats.velocidad == 6 && heroe.baseStats.inteligencia == 9 && heroe.trabajo.contains(Guerrero) && heroe.itemsEquipados.isEmpty)
  }

  @Test
  def `test_el_heroe_puede_equiparse_un_item`() = {
    val nuevoHeroe: Heroe = heroe.equiparUnItem(itemSaraza)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_puede_equiparse_una_cosa_en_cada_mano`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemMano)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemManoDos)
    assert(nuevoHeroe.itemsEquipados.size == 2)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_puede_equiparse_una_cosa_en_cada_mano_pero_no_una_tercera_y_agarra_la_ultima`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemMano)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemManoDos)
    assert(nuevoHeroe.itemsEquipados.size == 2)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemManoTres)
    assert(nuevoHeroe.itemsEquipados.size == 2)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10 + 20)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_puede_equiparse_una_cosa_en_cada_mano_pero_si_viene_una_para_las_dos_se_queda_con_esa`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemMano)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemManoDos)
    assert(nuevoHeroe.itemsEquipados.size == 2)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemDosManos)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 15)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_si_el_heroe_tiene_un_item_de_dos_manos_y_agarra_uno_de_una_se_queda_con_ese`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemDosManos)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 15)
    assert(nuevoHeroe.baseStats == stats)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemManoDos)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_se_equipa_con_algo_que_ya_tiene_ocupado_y_queda_bien`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemSaraza)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSaraza)
    assert(nuevoHeroe.itemsEquipados.size == 1)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
    assert(heroe.baseStats == stats)
  }

  @Test
  def `test_el_heroe_se_equipa_con_que_no_tiene_posicion`() = {
    var nuevoHeroe: Heroe = heroe.equiparUnItem(itemSaraza)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSinPosicion)
    nuevoHeroe = nuevoHeroe.equiparUnItem(itemSinPosicion)
    assert(nuevoHeroe.itemsEquipados.size == 3)
    assert(nuevoHeroe.getStats.hp == stats.hp + 10 + 10 + 10 + 10)
    assert(nuevoHeroe.baseStats == stats)
    assert(heroe.getStats.hp == stats.hp + 10)
    assert(heroe.itemsEquipados.isEmpty)
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


