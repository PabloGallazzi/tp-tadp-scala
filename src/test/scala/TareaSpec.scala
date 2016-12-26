import org.junit.Test

/**
  * Created by pgallazzi on 20/6/16.
  */
class TareaSpec extends BaseSpec{

  @Test
  def `test_la_tarea_devuelve_correctamente_el_calculador_para_heroe`() = {
    val resultado = tarea.realizarPorEquipo(equipo)
    assert(resultado.isInstanceOf[Exito])
    def exito: Exito = resultado.asInstanceOf[Exito]
    assert(resultado.equipo != equipo)
  }

  @Test
  def `test_la_tarea_devuelve_fracaso_cuando_no_puede_ser_realizada`() = {
    val resultado = tareaNoRealizable.realizarPorEquipo(equipo)
    assert(resultado.isInstanceOf[Fracaso])
    def exito: Fracaso = resultado.asInstanceOf[Fracaso]
    assert(resultado.equipo == equipo)
  }

}
