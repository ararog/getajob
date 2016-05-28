package br.net.bmobile.getajob.actors

import akka.actor.Actor
import br.net.bmobile.getajob.services.StatesService

/**
  * Created by rogerio on 28/05/2016.
  */
object StateActor {
  sealed trait Command
  case class Get(id: Long) extends Command
}

class StateActor extends Actor with StatesService{
  import StateActor._
  import akka.pattern.pipe

  def receive = {
    case Get(id) =>
      val originalSender = sender
      pipe(getStateById(id)) to originalSender
  }
}
