package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class EquipoTest extends BaseTest{

  @Test
  def `test_el_equipo_se_crea_correctamente`() = {
    assert(equipo.nombre == "EquipoSaraza" && equipo.oro == 0 && equipo.integrantes.size == 3)
  }

  @Test
  def `test_el_equipo_saca_el_mejor_correctamente`() = {
    assert(equipo.mejorHeroeSegun({ heroe => heroe.baseStats.hp }).contains(heroe2))
  }

  @Test
  def `test_el_equipo_puede_obtener_un_miembro_y_sigue_obteniendo_el_mejor`() = {
    val nuevoEquipo: Equipo = equipo.obtenerMiembro(heroe4)
    assert(nuevoEquipo.integrantes.size == 4)
    assert(nuevoEquipo.mejorHeroeSegun({ heroe => heroe.baseStats.hp }).contains(heroe4))
  }

  @Test
  def `test_el_equipo_puede_reemplazar_un_miembro_y_sigue_obteniendo_el_mejor`() = {
    val nuevoEquipo: Equipo = equipo.reemplazarMiembro(heroe2, heroe4)
    assert(nuevoEquipo.integrantes.size == 3)
    assert(nuevoEquipo.mejorHeroeSegun({ heroe => heroe.baseStats.hp }).contains(heroe4))
  }

  @Test
  def `test_el_equipo_vacio_devuelve_none_como_mejor`() = {
    assert(equipoVacio.mejorHeroeSegun({ heroe => heroe.baseStats.hp }).isEmpty)
  }

  @Test
  def `test_el_equipo_puede_obtener_lider`() = {
    assert(equipo.lider.contains(heroe3))
  }

  @Test
  def `test_el_equipo_vacío_devuelve_none_como_Lider`() = {
    assert(equipoVacio.lider.isEmpty)
  }
}
