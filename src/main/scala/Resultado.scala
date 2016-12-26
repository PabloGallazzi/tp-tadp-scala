

/**
  * Created by pgallazzi on 20/6/16.
  */
abstract class Resultado(val equipo: Equipo) {
  def map(f: (Equipo => Equipo)): Resultado

  def flatMap(f: (Equipo => Resultado)): Resultado

  def fold[T](e: (Equipo => T))(f: (Equipo => T)): T
}

case class Exito(override val equipo: Equipo) extends Resultado(equipo = equipo) {
  override def map(f: (Equipo) => Equipo): Resultado = Exito(f(equipo))

  override def flatMap(f: (Equipo) => Resultado): Resultado = f(equipo)

  override def fold[T](e: (Equipo) => T)(f: (Equipo) => T): T = f(equipo)
}

case class Fracaso(tarea: Tarea, override val equipo: Equipo) extends Resultado(equipo = equipo) {
  override def map(f: (Equipo) => Equipo): Resultado = this

  override def flatMap(f: (Equipo) => Resultado): Resultado = this

  override def fold[T](e: (Equipo) => T)(f: (Equipo) => T): T = e(equipo)
}