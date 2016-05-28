package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.City
import br.net.bmobile.getajob.utils.DatabaseConfig

/**
  * Created by rogerio on 3/28/16.
  */
trait CitiesTable extends DatabaseConfig with StatesTable {

  import driver.api._

  class Cities(tag: Tag) extends Table[City](tag, "city") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def stateId = column[Long]("state_id")
    def * = (id, name) <> ((City.apply _).tupled, City.unapply)

    def state = foreignKey("FK_CITY_STATE", stateId, states)(_.id)
  }

  protected val cities = TableQuery[Cities]
}
