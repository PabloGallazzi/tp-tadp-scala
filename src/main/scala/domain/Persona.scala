package domain

/**
  * Created by pgallazzi on 13/5/16.
  */
class Persona(var edad: Int) {
  def cumpliAnio = {
    edad += 1
  }
  def sosHumano = {
    true
  }

  def sosBebe = {
    edad < 2
  }

}
