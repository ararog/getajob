package br.net.bmobile.getajob.models.db

import br.net.bmobile.getajob.models.Candidate
import br.net.bmobile.getajob.utils.DatabaseConfig

/**
  * Created by rogerio on 3/28/16.
  */
trait CandidatesTable extends DatabaseConfig {

  import driver.api._

  class Candidates(tag: Tag) extends Table[Candidate](tag, "candidates") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> ((Candidate.apply _).tupled, Candidate.unapply)
  }

  protected val candidates = TableQuery[Candidates]
}
