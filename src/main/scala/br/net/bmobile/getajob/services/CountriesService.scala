package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.Country
import br.net.bmobile.getajob.models.db.CountriesTable

import scala.concurrent.Future

object CountriesService extends CountriesService

trait CountriesService extends CountriesTable {

  import driver.api._

  def getCountryById(id: Long): Future[Option[Country]] = db.run(countries.filter(_.id === id).result.headOption)
}