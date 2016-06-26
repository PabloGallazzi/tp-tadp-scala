package domain

import org.junit._

/**
  * Created by pgallazzi on 20/6/16.
  */
class MisionTest extends BaseTest{

  @Test
  def `test_la_mision_es_realizable`() = {
    val mision: Mision = new Mision(List(tarea, tarea), new masOroParaElEquipo(100))
    def resultado: Resultado = mision.realizarsePor(equipo).get
    assert(mision.realizarsePor(equipo).get.isInstanceOf[Exito])
    def exito: Exito = resultado.asInstanceOf[Exito]
    assert(exito.equipo != equipo)

  }

  @Test
  def `test_la_mision_no_es_realizable`() = {
    val mision: Mision = new Mision(List(tareaNoRealizable, tarea), new masOroParaElEquipo(100))
    def resultado: Resultado = mision.realizarsePor(equipo).get
    assert(mision.realizarsePor(equipo).get.isInstanceOf[Fracaso])
    def fracaso: Fracaso = resultado.asInstanceOf[Fracaso]
    assert(fracaso.tarea == tareaNoRealizable)
    assert(fracaso.equipo == equipo)
  }

}
