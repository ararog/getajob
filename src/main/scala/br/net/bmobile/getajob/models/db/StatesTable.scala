package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.State
import br.net.bmobile.getajob.utils.DatabaseConfig

/**
  * Created by rogerio on 3/28/16.
  */
trait StatesTable extends DatabaseConfig with CountriesTable {

  import driver.api._

  class States(tag: Tag) extends Table[State](tag, "states") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def countryId = column[Long]("country_id")
    def * = (id, name) <> ((State.apply _).tupled, State.unapply)

    def country = foreignKey("FK_STATE_COUNTRY", countryId, countries)(_.id)
  }

  val states = TableQuery[States]
}
