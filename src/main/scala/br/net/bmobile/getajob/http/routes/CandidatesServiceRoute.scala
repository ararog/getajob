package br.net.bmobile.getajob.http.routes

import akka.pattern.ask
import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import br.net.bmobile.getajob.actors.CandidateActor.Get
import br.net.bmobile.getajob.actors.{CandidateActor }
import br.net.bmobile.getajob.models.{Candidate, CandidateUpdate}
import br.net.bmobile.getajob.services.CandidatesService
import br.net.bmobile.getajob.utils.Security
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CandidatesServiceRoute extends CandidatesService with BaseServiceRoute with Security {

  private implicit val system = ActorSystem()

  val candidateActor = system.actorOf(Props(new CandidateActor))

  implicit val candidatesUpdateFormat = jsonFormat1(CandidateUpdate)

  val candidatesRoute = pathPrefix("candidates") {
    pathEndOrSingleSlash {
      get {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          complete(getUsers().map(_.toJson))
        }
      }
    } ~
    pathPrefix("me") {
      pathEndOrSingleSlash {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          get {
            complete(loggedUser)
          } ~
          post {
            entity(as[CandidateUpdate]) { candidateUpdate =>
              complete(updateCandidate(loggedUser.id.get, candidateUpdate).map(_.toJson))
            }
          }
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          get {
            val candidate:Future[Option[Candidate]] = (candidateActor ? Get(id)).mapTo[Option[Candidate]]
            complete(candidate.map(_.toJson))
          }
        }
      }
    }
  }

}
