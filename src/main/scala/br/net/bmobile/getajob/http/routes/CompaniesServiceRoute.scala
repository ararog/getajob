package br.net.bmobile.getajob.http.routes

import akka.pattern.ask
import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import br.net.bmobile.getajob.actors.CompanyActor
import br.net.bmobile.getajob.actors.CompanyActor.Get
import br.net.bmobile.getajob.models.{Company, CompanyUpdate}
import br.net.bmobile.getajob.services.CompaniesService
import br.net.bmobile.getajob.utils.Security
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CompaniesServiceRoute extends CompaniesService with BaseServiceRoute with Security {

  private implicit val system = ActorSystem()

  val companyActor = system.actorOf(Props(new CompanyActor))

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
            val company:Future[Option[Company]] = (companyActor ? Get(id)).mapTo[Option[Company]]
            complete(company.map(_.toJson))
          }
        }
      }
    }
  }
}
