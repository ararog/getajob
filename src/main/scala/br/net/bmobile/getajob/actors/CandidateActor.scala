package br.net.bmobile.getajob.actors

import akka.actor.Actor
import br.net.bmobile.getajob.services.CandidatesService

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by rogerio on 5/24/16.
  */
object CandidateActor {
  sealed trait Command
  case class Get(id: Long) extends Command
}

class CandidateActor extends Actor with CandidatesService{
  import CandidateActor._
  import akka.pattern.pipe

  def receive = {
    case Get(id) =>
      val originalSender = sender
      pipe(getCandidateById(id)) to originalSender
  }
}
