package br.net.bmobile.getajob.http.routes

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import akka.pattern.ask
import br.net.bmobile.getajob.actors.CandidateActor
import br.net.bmobile.getajob.actors.CandidateActor.Get
import br.net.bmobile.getajob.models.{Candidate, CandidateUpdate}
import br.net.bmobile.getajob.services.CandidatesService
import br.net.bmobile.getajob.utils.Security
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait EducationsServiceRoute extends CandidatesService with BaseServiceRoute with Security {

  private implicit val system = ActorSystem()

  val candidateActor = system.actorOf(Props(new CandidateActor))

  implicit val candidatesUpdateFormat = jsonFormat1(CandidateUpdate)

  val candidatesRoute = pathPrefix("educations") {
    pathEndOrSingleSlash {
      get {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          complete(getUsers().map(_.toJson))
        }
      }
    }
  }
}
