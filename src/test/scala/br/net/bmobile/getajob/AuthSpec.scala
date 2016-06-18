package br.net.bmobile.getajob

import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import br.net.bmobile.getajob.http.routes.AuthServiceRoute
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

/**
  * Created by rogerio on 18/06/2016.
  */
class AuthSpec extends ApiSpec with AuthServiceRoute {

  val log: LoggingAdapter = Logging(system, getClass)

  "Auth API" should {
    "Posting to /auth/signup should create the user" in {

      val json =
          (
            ("username" -> "Joe") ~
            ("password" -> "123456")
          )

      var jsonRequest = compact(render(json))

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/auth/signup",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))


      postRequest ~> authRoute ~> check {
        status.isSuccess() shouldEqual true
      }
    }
  }
}
