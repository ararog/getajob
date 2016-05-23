package br.net.bmobile.getajob.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import br.net.bmobile.getajob.models.{CandidateUpdate, User}
import br.net.bmobile.getajob.services.CandidatesService
import br.net.bmobile.getajob.utils.Security
import spray.json._

trait CandidatesServiceRoute extends CandidatesService with BaseServiceRoute with Security {

  import StatusCodes._

  implicit val candidatesUpdateFormat = jsonFormat1(CandidateUpdate)

  val candidatesRoute = pathPrefix("candidates") {
    pathEndOrSingleSlash {
      get {
        complete(getUsers().map(_.toJson))
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
            complete(getCandidateById(id).map(_.toJson))
          }
        }
      }
    }
  }

}
