package br.net.bmobile.getajob.actors

import akka.actor.Actor
import br.net.bmobile.getajob.services.CountriesService

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by rogerio on 28/05/2016.
  */
object CountryActor {
  sealed trait Command
  case class Get(id: Long) extends Command
}

class CountryActor extends Actor with CountriesService{
  import CountryActor._
  import akka.pattern.pipe

  def receive = {
    case Get(id) =>
      val originalSender = sender
      pipe(getCountryById(id)) to originalSender
  }
}
