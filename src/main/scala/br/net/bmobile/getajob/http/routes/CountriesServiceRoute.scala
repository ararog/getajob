package br.net.bmobile.getajob.http.routes

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import br.net.bmobile.getajob.actors.CandidateActor
import br.net.bmobile.getajob.actors.CompanyActor.Get
import br.net.bmobile.getajob.models.{CandidateUpdate, Company}
import br.net.bmobile.getajob.services.CandidatesService
import br.net.bmobile.getajob.utils.Security
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CountriesServiceRoute extends CandidatesService with BaseServiceRoute with Security {

  private implicit val system = ActorSystem()

  val candidateActor = system.actorOf(Props(new CandidateActor))

  implicit val candidatesUpdateFormat = jsonFormat1(CandidateUpdate)

  val candidatesRoute = pathPrefix("countries") {
    pathEndOrSingleSlash {
      get {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          complete(getUsers().map(_.toJson))
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          get {
            val company:Future[Option[Company]] = (companyActor ? Get(id)).mapTo[Option[Company]]
            complete(company.map(_.toJson))
          }
        }
      } ~
      pathPrefix("states") {
        pathEndOrSingleSlash {
          get {
            authenticator.bearerToken(acceptExpired = true) { loggedUser =>
              complete(getUsers().map(_.toJson))
            }
          }
        }
      } ~
      pathPrefix(IntNumber) { id =>
        pathEndOrSingleSlash {
          authenticator.bearerToken(acceptExpired = true) { loggedUser =>
            get {
              val company: Future[Option[Company]] = (companyActor ? Get(id)).mapTo[Option[Company]]
              complete(company.map(_.toJson))
            }
          }
        } ~
        pathPrefix("cities") {
          pathEndOrSingleSlash {
            get {
              authenticator.bearerToken(acceptExpired = true) { loggedUser =>
                complete(getUsers().map(_.toJson))
              }
            }
          }
        } ~
        pathPrefix(IntNumber) { id =>
          pathEndOrSingleSlash {
            authenticator.bearerToken(acceptExpired = true) { loggedUser =>
              get {
                val company: Future[Option[Company]] = (companyActor ? Get(id)).mapTo[Option[Company]]
                complete(company.map(_.toJson))
              }
            }
          }
        }
      }
    }
  }
}
