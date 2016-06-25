package domain

import org.junit.Before

/**
  * Created by pgallazzi on 20/6/16.
  */
class BaseTest {

  @Before
  val stats: Stats = new Stats(10, 5, 6, 9)
  val stats2: Stats = new Stats(12, 5, 6, 9)
  val stats3: Stats = new Stats(11, 5, 6, 9)
  val stats4: Stats = new Stats(19, 5, 6, 9)
  val stats5: Stats = new Stats(19, 5, 6, 9)

  val heroe: Heroe = new Heroe(stats, Some(Guerrero))
  val heroe2: Heroe = new Heroe(stats2, Some(Guerrero))
  val heroe3: Heroe = new Heroe(stats3, Some(Guerrero))
  val heroe4: Heroe = new Heroe(stats4, Some(Mago))
  val heroe5: Heroe = new Heroe(stats5, None)

  val equipo: Equipo = new Equipo("EquipoSaraza", List(heroe, heroe2, heroe3))

  val equipoSinTrabajos = new Equipo("EquipoSinTrabajos", List(heroe5))

  def equipoVacio: Equipo = new Equipo("Equipo vacio")

  val itemSaraza: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => var puede: Boolean = true
    puede = puede && (heroe.trabajo match {
      case Some(Guerrero) => true
      case _ => false
    })
    puede = puede && heroe.getStats.fuerza >= 5
    puede
  }, Some(Cabeza), 100)

  val itemMano: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => true
  }, Some(ManoDerecha))

  val itemManoDos: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => true
  }, Some(ManoIzquierda))

  val itemManoTres: Item = new Item({ stats => stats.copy(hp = stats.hp + 20) }, { heroe => true
  }, Some(ManoDerecha))

  val itemDosManos: Item = new Item({ stats => stats.copy(hp = stats.hp + 15) }, { heroe => true
  }, Some(DosManos))

  val itemSinPosicion: Item = new Item({ stats => stats.copy(hp = stats.hp + 10) }, { heroe => var puede: Boolean = true
    puede = puede && (heroe.trabajo match {
      case Some(Guerrero) => true
      case _ => false
    })
    puede = puede && heroe.getStats.fuerza >= 5
    puede
  }, None)

  val tarea: Tarea = new Tarea(
    { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
      equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 10
    }, { heroe => heroe.trabajo match {
      case Some(Mago) => 10
      case _ => 20
    }
    }, { heroe => heroe.cambiarDeTrabajo(Some(Mago)) })

  val tareaNoRealizable: Tarea = new Tarea(
    { equipo => equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).isDefined &&
      equipo.mejorHeroeSegun(heroe => heroe.getStats.hp).get.getStats.hp > 100
    }, { heroe => heroe.trabajo match {
      case Some(Mago) => 10
      case _ => 20
    }
    }, { heroe => heroe })
}
