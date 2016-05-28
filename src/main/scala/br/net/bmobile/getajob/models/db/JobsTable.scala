package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.Job
import br.net.bmobile.getajob.utils.DatabaseConfig

/**
  * Created by rogerio on 3/28/16.
  */
trait JobsTable extends DatabaseConfig with CompaniesTable {

  import driver.api._

  class Jobs(tag: Tag) extends Table[Job](tag, "jobs") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def description = column[String]("description")
    def zipcode = column[String]("zipcode")
    def companyId = column[Long]("company_id")
    def * = (id, title, description, zipcode) <> ((Job.apply _).tupled, Job.unapply)

    def company = foreignKey("FK_JOB_COMPANY", companyId, companies)(_.id)
  }

  protected val jobs = TableQuery[Jobs]
}
