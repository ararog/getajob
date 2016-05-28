package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.City
import br.net.bmobile.getajob.models.db.CitiesTable

import scala.concurrent.Future

object CitiesService extends CitiesService

trait CitiesService extends CitiesTable {

  import driver.api._

  def getCityById(id: Long): Future[Option[City]] = db.run(cities.filter(_.id === id).result.headOption)
}