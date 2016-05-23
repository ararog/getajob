package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.{User, UserUpdate}
import br.net.bmobile.getajob.models.db.UsersTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object UsersService extends UsersService

trait UsersService extends UsersTable {

  import driver.api._

  def getUsers(): Future[Seq[User]] = db.run(users.result)

  def getUserById(id: Long): Future[Option[User]] = db.run(users.filter(_.id === id).result.headOption)

  def getUserByLogin(login: String): Future[Option[User]] = db.run(users.filter(_.username === login).result.headOption)

  def createUser(user: User): Future[User] = db.run(users returning users += user.withHashedPassword())

  def updateUser(id: Long, userUpdate: UserUpdate): Future[Option[User]] = getUserById(id).flatMap {
    case Some(user) =>
      val updatedUser = userUpdate.merge(user)
      db.run(users.filter(_.id === id).update(updatedUser)).map(_ => Some(updatedUser))
    case None => Future.successful(None)
  }

  def deleteUser(id: Long): Future[Int] = db.run(users.filter(_.id === id).delete)

}