package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.Country
import br.net.bmobile.getajob.utils.DatabaseConfig

/**
  * Created by rogerio on 3/28/16.
  */
trait CountriesTable extends DatabaseConfig {

  import driver.api._

  class Countries(tag: Tag) extends Table[Country](tag, "countries") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (id, name) <> ((Country.apply _).tupled, Country.unapply)
  }

  protected val countries = TableQuery[Countries]
}
