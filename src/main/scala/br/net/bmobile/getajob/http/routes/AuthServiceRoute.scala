package br.net.bmobile.getajob.http.routes

import java.time.Instant

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import br.net.bmobile.getajob.models.User
import br.net.bmobile.getajob.utils.Security
import de.choffmeister.auth.common.{JsonWebToken, OAuth2AccessTokenResponse, OAuth2AccessTokenResponseFormat}
import spray.json._

trait AuthServiceRoute extends BaseServiceRoute with Security {

  import StatusCodes._

  case class LoginPassword(login: String, password: String)

  implicit val loginPasswordFormat = jsonFormat2(LoginPassword)
  val authRoute = pathPrefix("auth") {
    path("signin") {
      pathEndOrSingleSlash {
        post {
          entity(as[LoginPassword]) { loginPassword =>
            onSuccess(signIn(loginPassword.login, loginPassword.password)) {
              user => user match {
                case Some(user) => completeWithToken(user)
                case None => reject
              }
            }
          }
        }
      }
    } ~
      path("signup") {
        pathEndOrSingleSlash {
          post {
            entity(as[User]) { user =>
              complete(Created -> signUp(user).map(_.toJson))
            }
          }
        }
      }
  }

  private def completeWithToken(user: User): Route = {
    val secret = bearerTokenSecret
    val lifetime = bearerTokenLifetime.toSeconds
    val now = System.currentTimeMillis / 1000L

    val token = JsonWebToken(
      claims = Map("id" -> JsString(user.id.toString), "name" -> JsString(user.username)),
      createdAt = Instant.ofEpochSecond(now),
      expiresAt = Instant.ofEpochSecond(now + lifetime)
    )
    val tokenStr = JsonWebToken.write(token, secret)

    val response = OAuth2AccessTokenResponse("bearer", tokenStr, lifetime)
    complete(OAuth2AccessTokenResponseFormat.write(response).compactPrint)
  }
}
