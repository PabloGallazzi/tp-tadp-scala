/**
  * Created by pgallazzi on 20/6/16.
  */

object Cuerpo {
  def conflictuan(cuerpos: (Cuerpo, Cuerpo)): Boolean = cuerpos match {
    case (DosManos, ManoDerecha) | (ManoDerecha, DosManos) => true
    case (DosManos, ManoIzquierda) | (ManoIzquierda, DosManos) => true
    case (ParteQueNoInterfiere, _) | (_, ParteQueNoInterfiere) => false
    case (x, y) => x == y
  }
}

abstract class Cuerpo

object ParteQueNoInterfiere extends Cuerpo

object Cabeza extends Cuerpo

abstract class Mano extends Cuerpo

object ManoDerecha extends Mano

object ManoIzquierda extends Mano

object DosManos extends Cuerpo

object Torso extends Cuerpo
