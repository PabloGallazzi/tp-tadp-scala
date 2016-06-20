package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class TareaTest extends BaseTest{

  @Test
  def `test_la_tarea_devuelve_correctamente_el_calculador_para_heroe`() = {
    assert(tarea.realizarPorEquipo(equipo).isDefined)
  }

  @Test
  def `test_la_tarea_devuelve_none_cuando_no_puede_ser_realizada`() = {
    assert(tareaNoRealizable.realizarPorEquipo(equipo).isEmpty)
  }

}
