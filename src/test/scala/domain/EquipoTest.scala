package domain

import org.junit.{Before, Test}

/**
  * Created by pgallazzi on 20/6/16.
  */
class EquipoTest {

  @Before
  val stats: Stats = new Stats(10, 5, 6, 9)
  val stats2: Stats = new Stats(12, 5, 6, 9)
  val stats3: Stats = new Stats(11, 5, 6, 9)
  val stats4: Stats = new Stats(19, 5, 6, 9)
  val heroe: Heroe = new Heroe(stats, Some(Guerrero))
  val heroe2: Heroe = new Heroe(stats2, Some(Guerrero))
  val heroe3: Heroe = new Heroe(stats3, Some(Guerrero))
  val heroe4: Heroe = new Heroe(stats4, Some(Mago))
  val equipo: Equipo = new Equipo("EquipoSaraza", List(heroe, heroe2, heroe3))
  def equipoVacio: Equipo = new Equipo("Equipo vacio")

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
