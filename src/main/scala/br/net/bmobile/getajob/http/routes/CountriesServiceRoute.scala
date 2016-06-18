package br.net.bmobile.getajob.http.routes

import akka.pattern.ask
import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import br.net.bmobile.getajob.actors.{CityActor, CountryActor, StateActor}
import br.net.bmobile.getajob.models.{Country, State, City}
import br.net.bmobile.getajob.services.{CountriesService}
import br.net.bmobile.getajob.utils.Security
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CountriesServiceRoute extends BaseServiceRoute with Security {

  private implicit val system = ActorSystem()

  val countryActor = system.actorOf(Props(new CountryActor))
  val stateActor = system.actorOf(Props(new StateActor))
  val cityActor = system.actorOf(Props(new CityActor))

  val countriesRoute = pathPrefix("countries") {
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
            val country:Future[Option[Country]] = (countryActor ? CountryActor.Get(id)).mapTo[Option[Country]]
            complete(country.map(_.toJson))
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
              val state: Future[Option[State]] = (stateActor ? StateActor.Get(id)).mapTo[Option[State]]
              complete(state.map(_.toJson))
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
                val city: Future[Option[City]] = (cityActor ? CityActor.Get(id)).mapTo[Option[City]]
                complete(city.map(_.toJson))
              }
            }
          }
        }
      }
    }
  }
}
