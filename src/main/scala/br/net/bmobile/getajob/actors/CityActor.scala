package br.net.bmobile.getajob.actors

import akka.actor.Actor
import br.net.bmobile.getajob.services.CitiesService

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by rogerio on 28/05/2016.
  */
object CityActor {
  sealed trait Command
  case class Get(id: Long) extends Command
}

class CityActor extends Actor with CitiesService{
  import CityActor._
  import akka.pattern.pipe

  def receive = {
    case Get(id) =>
      val originalSender = sender
      pipe(getCityById(id)) to originalSender
  }
}
