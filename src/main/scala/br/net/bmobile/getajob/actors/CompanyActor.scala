package br.net.bmobile.getajob.actors

import akka.actor.Actor
import br.net.bmobile.getajob.services.CompaniesService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by rogerio on 5/24/16.
  */
object CompanyActor {
  sealed trait Command
  case class Get(id: Long) extends Command
}

class CompanyActor extends Actor with CompaniesService {
  import CompanyActor._
  import akka.pattern.pipe

  def receive = {
    case Get(id) =>
      val originalSender = sender
      pipe(getCompanyById(id)) to originalSender
  }
}