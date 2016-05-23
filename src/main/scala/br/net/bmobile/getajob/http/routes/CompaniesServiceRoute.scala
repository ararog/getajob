package br.net.bmobile.getajob.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import br.net.bmobile.getajob.models.{Company, CompanyUpdate}
import br.net.bmobile.getajob.services.CompaniesService
import br.net.bmobile.getajob.utils.Security
import spray.json._

trait CompaniesServiceRoute extends CompaniesService with BaseServiceRoute with Security {

  import StatusCodes._

  implicit val companiesUpdateFormat = jsonFormat1(CompanyUpdate)

  val companiesRoute = pathPrefix("companies") {
    pathEndOrSingleSlash {
      authenticator.bearerToken(acceptExpired = true) { loggedUser =>
        get {
          complete(getCompanies().map(_.toJson))
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
            entity(as[CompanyUpdate]) {companyUpdate =>
              complete(updateCompany(loggedUser.id.get, companyUpdate).map(_.toJson))
            }
          }
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        authenticator.bearerToken(acceptExpired = true) { loggedUser =>
          get {
            complete(getCompanyById(id).map(_.toJson))
          }
        }
      }
    }
  }
}
