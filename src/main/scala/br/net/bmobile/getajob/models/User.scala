package br.net.bmobile.getajob.models

import org.mindrot.jbcrypt.BCrypt

case class User(id: Option[Long] = None, username: String, password: String) {
  require(!username.isEmpty, "username.empty")
  require(!password.isEmpty, "password.empty")

  def withHashedPassword(): User = this.copy(password = BCrypt.hashpw(password, BCrypt.gensalt()))
}

case class UserUpdate(username: Option[String] = None, password: Option[String] = None) {
  def merge(user: User): User = {
    User(user.id, username.getOrElse(user.username), password.map(ps => BCrypt.hashpw(ps, BCrypt.gensalt())).getOrElse(user.password))
  }
}