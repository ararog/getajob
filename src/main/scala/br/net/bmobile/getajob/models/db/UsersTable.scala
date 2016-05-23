package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.User
import br.net.bmobile.getajob.utils.DatabaseConfig

trait UsersTable extends DatabaseConfig {

  import driver.api._

  class Users(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username")
    def password = column[String]("password")

    def * = (id, username, password) <> ((User.apply _).tupled, User.unapply)
  }

  protected val users = TableQuery[Users]

}
