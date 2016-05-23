package br.net.bmobile.getajob.utils

import br.net.bmobile.getajob.models.User
import br.net.bmobile.getajob.services.{AuthService, UsersService}
import de.choffmeister.auth.akkahttp.Authenticator

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

trait Security extends AuthService with UsersService {

  val bearerTokenSecret = "bemobile".getBytes
  val bearerTokenLifetime = 7.days

  val authenticator = new Authenticator[User](
    realm = "Example realm",
    bearerTokenSecret = bearerTokenSecret,
    fromUsernamePassword = (username, password) => signIn(username, password),
    fromBearerToken = token => getUserById(token.claimAsString("id").right.get.toLong))
}