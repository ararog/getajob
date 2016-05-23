package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.Company
import br.net.bmobile.getajob.utils.DatabaseConfig

trait CompaniesTable extends DatabaseConfig {

  import driver.api._

  class Companies(tag: Tag) extends Table[Company](tag, "companies") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> ((Company.apply _).tupled, Company.unapply)
  }

  protected val companies = TableQuery[Companies]

}
