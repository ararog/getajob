package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.User
import br.net.bmobile.getajob.models.db.UsersTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object AuthService extends AuthService

trait AuthService extends UsersTable {

  import driver.api._

  def signIn(login: String, password: String): Future[Option[User]] = {
    db.run(users.filter(u => u.username === login).result).flatMap { users =>
      users.find(user => password == user.password) match {
        case Some(user) => Future.successful(Some(user))
        case None => Future.successful(None)
      }
    }
  }

  def signUp(newUser: User): Future[User] = {
    UsersService.createUser(newUser)
  }
}
