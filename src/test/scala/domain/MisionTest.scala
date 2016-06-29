package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class MisionTest extends BaseTest{

  @Test
  def `test_la_mision_es_realizable`() = {
    val mision: Mision = new Mision(List(tarea, tarea), new masOroParaElEquipo(100))
    def resultado: Resultado = mision.realizarsePor(equipo)
    assert(mision.realizarsePor(equipo).isInstanceOf[Exito])
    def exito: Exito = resultado.asInstanceOf[Exito]
    assert(exito.equipo.oro == 100)
    assert(exito.equipo.integrantes.size == 3)

  }

  @Test
  def `test_la_mision_no_es_realizable`() = {
    val mision: Mision = new Mision(List(tarea, tareaNoRealizable), new masOroParaElEquipo(100))
    def resultado: Resultado = mision.realizarsePor(equipo)
    assert(mision.realizarsePor(equipo).isInstanceOf[Fracaso])
    def fracaso: Fracaso = resultado.asInstanceOf[Fracaso]
    assert(fracaso.tarea == tareaNoRealizable)
    assert(fracaso.equipo.oro == 0)
    assert(fracaso.equipo.integrantes.size == 3)
  }

}
