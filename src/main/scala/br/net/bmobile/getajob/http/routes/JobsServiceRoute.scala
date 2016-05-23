package br.net.bmobile.getajob.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import br.net.bmobile.getajob.services.JobsService
import br.net.bmobile.getajob.utils.Security
import spray.json._

/**
  * Created by rogerio on 3/28/16.
  */
trait JobsServiceRoute extends JobsService with BaseServiceRoute with Security {

  import StatusCodes._

  val jobsRoute = pathPrefix("jobs") {
    pathPrefix("search") {
      pathEndOrSingleSlash {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          get {
            complete(NoContent)
          }
        }
      }
    } ~
      pathPrefix(IntNumber) { id =>
        pathEndOrSingleSlash {
          authenticator.bearerToken(acceptExpired = true) { loggedUser =>
            get {
              complete(getJobById(id).map(_.toJson))
            }
          }
        }
      }
  }
}
