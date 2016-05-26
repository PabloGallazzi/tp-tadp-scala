package domain

/**
  * Created by pgallazzi on 13/5/16.
  */
class Persona(var edad: Int) {

  var conocidos = Set[Persona]()

  def cumpliAnio = {
    edad += 1
  }
  def sosHumano = {
    true
  }

  def sosBebe = {
    edad < 2
  }

  def sosMayor ={
    edad >= 21
  }

  def conocer(persona: Persona){
    conocidos += persona
  }

}
