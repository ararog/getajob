package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.State
import br.net.bmobile.getajob.models.db.StatesTable

import scala.concurrent.Future

object StatesService extends StatesService

trait StatesService extends StatesTable {

  import driver.api._

  def getStateById(id: Long): Future[Option[State]] = db.run(states.filter(_.id === id).result.headOption)
}