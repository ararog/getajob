package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.Job
import br.net.bmobile.getajob.utils.DatabaseConfig

/**
  * Created by rogerio on 3/28/16.
  */
trait JobsTable extends DatabaseConfig {

  import driver.api._

  class Jobs(tag: Tag) extends Table[Job](tag, "jobs") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def description = column[String]("description")

    def * = (id, title, description) <> ((Job.apply _).tupled, Job.unapply)
  }

  protected val jobs = TableQuery[Jobs]
}
